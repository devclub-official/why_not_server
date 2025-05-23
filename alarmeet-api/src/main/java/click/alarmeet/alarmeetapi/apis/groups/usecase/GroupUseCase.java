package click.alarmeet.alarmeetapi.apis.groups.usecase;

import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupConstants.*;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupMapper;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSaveService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
import click.alarmeet.alarmeetapi.apis.users.service.UserSearchService;
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

	private final GroupSaveService groupSaveService;
	private final GroupSearchService groupSearchService;

	private final UserSearchService userSearchService;

	public void createGroup(String userId, GroupCreateReq groupReq) {
		ObjectId userOid = new ObjectId(userId);

		if (groupSearchService.countByOwnerId(userOid) >= GROUP_COUNT_MAX) {
			throw new GlobalErrorException(GroupErrorCode.GROUP_COUNT_LIMIT_EXCEEDED);
		}

		Group group = groupSaveService.create(
			groupMapper.toGroup(
				userOid,
				groupReq.group(),
				groupUserMapper.toGroupUser(userOid, GroupRole.LEADER, groupReq.user())
			)
		);

		userSearchService.addGroupId(userOid, group.getId());
	}
}
