package click.alarmeet.alarmeetapi.apis.groups.usecase;

import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.constant.GroupInviteCodeConstants.*;
import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeErrorCode.*;
import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupConstants.*;

import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeException;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.mapper.GroupInviteCodeMapper;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.service.GroupInviteCodeCreateService;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.service.GroupInviteCodeSearchService;
import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto.GroupInviteCodeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupUpdateDto.GroupUpdateReq;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorException;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupMapper;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupCreateService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupDeleteService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupUpdateService;
import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetapi.apis.users.service.UserDeleteService;
import click.alarmeet.alarmeetapi.apis.users.service.UserSearchService;
import click.alarmeet.alarmeetapi.apis.users.service.UserUpdateService;
import click.alarmeet.alarmeetapi.common.annotation.UseCase;
import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GroupUseCase {
	private final GroupMapper groupMapper;
	private final GroupUserMapper groupUserMapper;
	private final GroupInviteCodeMapper groupInviteCodeMapper;

	private final GroupCreateService groupSaveService;
	private final GroupSearchService groupSearchService;
	private final GroupUpdateService groupUpdateService;
	private final GroupDeleteService groupDeleteService;

	private final UserSearchService userSearchService;
	private final UserUpdateService userSaveService;
	private final UserDeleteService userDeleteService;

	private final GroupInviteCodeCreateService groupInviteCodeCreateService;
	private final GroupInviteCodeSearchService groupInviteCodeSearchService;

	public void createGroup(String userId, GroupCreateReq groupReq) {
		ObjectId userOid = new ObjectId(userId);

		if (groupSearchService.countByLeaderId(userOid) >= GROUP_CREATE_MAX_COUNT) {
			throw new GlobalErrorException(GroupErrorCode.GROUP_COUNT_LIMIT_EXCEEDED);
		}

		Group group = groupSaveService.create(
			groupMapper.toGroup(
				userOid,
				groupReq.group(),
				groupUserMapper.toGroupUser(userOid, GroupRole.LEADER, groupReq.user())
			)
		);

		userSaveService.addGroupId(userOid, group.getId());
	}

	public GroupListRes getGroups(String userId) {
		ObjectId userOid = new ObjectId(userId);
		User user = userSearchService.findUser(userOid);

		return new GroupListRes(groupSearchService.findGroups(user.getGroupIds()));
	}

	public GroupDetailRes getGroup(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.findGroup(groupId);

		if (!group.isUserExist(userOid)) {
			throw new GroupErrorException(GroupErrorCode.USER_NOT_IN_GROUP);
		}

		return groupMapper.toGroupDetailRes(group);
	}

	public void updateGroup(ObjectId groupId, String userId, GroupUpdateReq groupReq) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.findGroup(groupId);

		if (!group.isManagerOrHigherUser(userOid)) {
			throw new GroupErrorException(GroupErrorCode.ROLE_NOT_ALLOWED);
		}

		groupUpdateService.updateGroup(groupId, groupReq.toUpdateMap());
	}

	public void deleteGroup(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.findGroup(groupId);

		if (!group.isLeaderUser(userOid)) {
			throw new GroupErrorException(GroupErrorCode.ROLE_NOT_ALLOWED);
		}

		groupDeleteService.deleteGroup(groupId, userOid);

		userDeleteService.deleteGroupId(userOid, groupId);
	}

	public GroupInviteCodeRes createGroupInviteCode(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.findGroup(groupId);

		if (!group.isManagerOrHigherUser(userOid)) {
			throw new GroupErrorException(GroupErrorCode.ROLE_NOT_ALLOWED);
		}

		for (int attempt = 0; attempt < CREATE_MAX_ATTEMPT; attempt++) {
			try {
				// 초대 코드 생성 시도
				return groupInviteCodeMapper.toGroupInviteCodeRes(
					groupInviteCodeCreateService.insert(GroupInviteCode.createNewWithCode(groupId))
				);
			} catch (DuplicateKeyException dke) {
				// group id에 이미 코드가 존재하는 경우
				if (dke.getMessage().contains("groupId")) {
					try {
						// 기존 코드 반환 시도
						return groupInviteCodeMapper.toGroupInviteCodeRes(
							groupInviteCodeSearchService.findByGroupId(groupId)
						);
					} catch (GroupInviteCodeException gice) {
						// group id에 발급 받은 코드 없으면 404
						// 작업 중 코드 만료
						continue;
					}
				}
			}
		}

		throw new GroupInviteCodeException(CODE_CREATION_RETRY_EXCEEDED);
	}

	public GroupInviteCodeRes getGroupInviteCode(ObjectId groupId) {
		return groupInviteCodeMapper.toGroupInviteCodeRes(
			groupInviteCodeSearchService.findByGroupId(groupId)
		);
	}
}
