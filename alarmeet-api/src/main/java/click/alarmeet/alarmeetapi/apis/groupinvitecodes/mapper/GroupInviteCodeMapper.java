package click.alarmeet.alarmeetapi.apis.groupinvitecodes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto.GroupInviteCodeRes;
import click.alarmeet.alarmeetapi.common.mapper.MapStructBaseConfig;

@Mapper(config = MapStructBaseConfig.class)
public interface GroupInviteCodeMapper {
	@Mapping(target = "code", source = "id")
	GroupInviteCodeRes toGroupInviteCodeRes(GroupInviteCode groupInviteCode);
}
