package click.alarmeet.alarmeetapi.apis.groups.usecase;

import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupConstants.*;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupMapper;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSaveService;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSearchService;
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

	public Group createGroup(String userId, GroupCreateReq groupReq) {
		if (groupSearchService.countByOwnerId(userId) > GROUP_COUNT_MAX) {
			throw new GlobalErrorException(GroupErrorCode.GROUP_COUNT_LIMIT_EXCEEDED);
		}

		return groupSaveService.create(
			groupMapper.toGroup(
				userId,
				groupReq.group(),
				groupUserMapper.toGroupUser(userId, GroupRole.LEADER, groupReq.user())
			)
		);
	}
}
