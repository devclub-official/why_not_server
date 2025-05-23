package click.alarmeet.alarmeetapi.apis.groups.usecase;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupMapper;
import click.alarmeet.alarmeetapi.apis.groups.mapper.GroupUserMapper;
import click.alarmeet.alarmeetapi.apis.groups.service.GroupSaveService;
import click.alarmeet.alarmeetapi.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GroupUseCase {
	private final GroupMapper groupMapper;
	private final GroupUserMapper groupUserMapper;
	private final GroupSaveService groupSaveService;

	public Group createGroup(String userId, GroupCreateReq groupReq) {
		return groupSaveService.create(
			groupMapper.toGroup(
				userId,
				groupReq.group(),
				groupUserMapper.toGroupUser(userId, GroupRole.LEADER, groupReq.user())
			)
		);
	}
}
