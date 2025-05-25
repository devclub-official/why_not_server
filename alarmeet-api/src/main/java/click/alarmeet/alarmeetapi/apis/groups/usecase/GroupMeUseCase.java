package click.alarmeet.alarmeetapi.apis.groups.usecase;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeDto.GroupMeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeUpdateDto.GroupMeUpdateReq;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorException;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupUpdateService;
import click.alarmeet.alarmeetapi.common.annotation.UseCase;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GroupMeUseCase {
	private final GroupUserMapper groupUserMapper;

	private final GroupSearchService groupSearchService;
	private final GroupUpdateService groupUpdateService;

	public GroupMeRes getGroupUser(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		return groupUserMapper.toGroupMeRes(groupSearchService.getUser(groupId, userOid));
	}

	public void updateGroupUser(ObjectId groupId, String userId, GroupMeUpdateReq meReq) {
		ObjectId userOid = new ObjectId(userId);

		MongoCountResult mongoCountResult = groupUpdateService.updateUser(
			groupId,
			userOid,
			meReq.nickname(),
			meReq.profileImageUrl()
		);
		if (!mongoCountResult.isMatched()) {
			throw new GroupErrorException(GroupErrorCode.GROUP_USER_NOT_FOUND);
		}
	}
}
