package click.alarmeet.alarmeetapi.apis.groups.usecase;

import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupConstants.*;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
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
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GroupUseCase {
	private final GroupMapper groupMapper;
	private final GroupUserMapper groupUserMapper;

	private final GroupCreateService groupCreateService;
	private final GroupSearchService groupSearchService;
	private final GroupUpdateService groupUpdateService;
	private final GroupDeleteService groupDeleteService;

	private final UserSearchService userSearchService;
	private final UserUpdateService userUpdateService;
	private final UserDeleteService userDeleteService;

	public void createGroup(String userId, GroupCreateReq groupReq) {
		ObjectId userOid = new ObjectId(userId);

		if (groupSearchService.countByLeaderId(userOid) >= GROUP_CREATE_MAX_COUNT) {
			throw new GlobalErrorException(GroupErrorCode.GROUP_COUNT_LIMIT_EXCEEDED);
		}

		Group group = groupCreateService.save(
			groupMapper.toGroup(
				userOid,
				groupReq.group(),
				groupUserMapper.toGroupUser(userOid, GroupRole.LEADER, groupReq.user())
			)
		);

		userUpdateService.addGroupId(userOid, group.getId());
	}

	public GroupListRes getGroups(String userId) {
		ObjectId userOid = new ObjectId(userId);
		User user = userSearchService.find(userOid);

		return new GroupListRes(groupSearchService.findAll(user.getGroupIds()));
	}

	public GroupDetailRes getGroup(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.find(groupId);

		if (!group.isUserExist(userOid)) {
			throw new GroupErrorException(GroupErrorCode.USER_NOT_IN_GROUP);
		}

		return groupMapper.toGroupDetailRes(group);
	}

	public void updateGroup(ObjectId groupId, String userId, GroupUpdateReq groupReq) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.find(groupId);

		if (!group.isManagerOrHigherUser(userOid)) {
			throw new GroupErrorException(GroupErrorCode.ROLE_NOT_ALLOWED);
		}

		MongoCountResult mongoCountResult = groupUpdateService.update(groupId, groupReq.toUpdateMap());
		if (!mongoCountResult.isMatched()) {
			throw new GroupErrorException(GroupErrorCode.GROUP_NOT_FOUND);
		}
	}

	public void deleteGroup(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		Group group = groupSearchService.find(groupId);

		if (!group.isLeaderUser(userOid)) {
			throw new GroupErrorException(GroupErrorCode.ROLE_NOT_ALLOWED);
		}

		groupDeleteService.delete(groupId, userOid);

		userDeleteService.deleteGroupId(userOid, groupId);
	}
}
