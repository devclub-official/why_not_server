package click.alarmeet.alarmeetapi.apis.groups.usecase;

import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.constant.GroupInviteCodeConstants.*;
import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeErrorCode.*;

import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeException;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.mapper.GroupInviteCodeMapper;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.service.GroupInviteCodeCreateService;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.service.GroupInviteCodeSearchService;
import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupByCodeDto.GroupByCodeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorException;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupMapper;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupUpdateService;
import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetapi.apis.users.service.UserSearchService;
import click.alarmeet.alarmeetapi.apis.users.service.UserUpdateService;
import click.alarmeet.alarmeetapi.common.annotation.UseCase;
import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GroupInviteUseCase {
	private final GroupMapper groupMapper;
	private final GroupUserMapper groupUserMapper;

	private final GroupInviteCodeMapper groupInviteCodeMapper;

	private final GroupSearchService groupSearchService;
	private final GroupUpdateService groupUpdateService;

	private final GroupInviteCodeCreateService groupInviteCodeCreateService;
	private final GroupInviteCodeSearchService groupInviteCodeSearchService;

	private final UserSearchService userSearchService;
	private final UserUpdateService userUpdateService;

	public GroupInviteCodeDto.GroupInviteCodeRes createGroupInviteCode(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.find(groupId);

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

	public GroupInviteCodeDto.GroupInviteCodeRes getGroupInviteCode(ObjectId groupId) {
		return groupInviteCodeMapper.toGroupInviteCodeRes(
			groupInviteCodeSearchService.findByGroupId(groupId)
		);
	}

	public void joinGroup(String userId, GroupJoinDto.GroupJoinReq joinReq) {
		ObjectId userOid = new ObjectId(userId);

		GroupInviteCode groupInviteCode = groupInviteCodeSearchService.find(joinReq.code());

		User user = userSearchService.find(userOid);
		if (user.isGroupIdExist(groupInviteCode.getGroupId())) {
			return;
		}

		long leaderCount = groupSearchService.countByLeaderId(userOid);

		if (!user.canJoinGroup(leaderCount)) {
			throw new GlobalErrorException(GroupErrorCode.GROUP_COUNT_LIMIT_EXCEEDED);
		}

		MongoCountResult mongoCountResult = groupUpdateService.addUser(
			groupInviteCode.getGroupId(),
			groupUserMapper.toGroupUser(userOid, GroupRole.MEMBER, joinReq)
		);
		if (mongoCountResult.isModified()) {
			// 수정이 되면 유저에 추가
			userUpdateService.addGroupId(userOid, groupInviteCode.getGroupId());
		} else if (!mongoCountResult.isMatched()) {
			// 매치도 안된 경우 404
			throw new GroupErrorException(GroupErrorCode.GROUP_NOT_FOUND);
		}
	}

	public GroupByCodeRes getGroupByCode(String code) {
		GroupInviteCode groupInviteCode = groupInviteCodeSearchService.find(code);

		Group group = groupSearchService.find(groupInviteCode.getGroupId());

		return groupMapper.toGroupByCodeRes(group, groupInviteCode.getExpiredAt());
	}
}
