package click.alarmeet.alarmeetapi.apis.groups.usecase;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeDto.GroupMeRes;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
import click.alarmeet.alarmeetapi.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GroupMeUseCase {
	private final GroupUserMapper groupUserMapper;

	private final GroupSearchService groupSearchService;

	public GroupMeRes getGroupUser(ObjectId groupId, String userId) {
		ObjectId userOid = new ObjectId(userId);
		return groupUserMapper.toGroupMeRes(groupSearchService.getUser(groupId, userOid));
	}
}
