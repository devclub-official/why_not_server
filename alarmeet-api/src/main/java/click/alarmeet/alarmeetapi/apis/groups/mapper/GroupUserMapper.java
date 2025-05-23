package click.alarmeet.alarmeetapi.apis.groups.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto;
import click.alarmeet.alarmeetapi.config.MapStructBaseConfig;

@Mapper(config = MapStructBaseConfig.class)
public interface GroupUserMapper {
	@Mapping(target = "id", source = "userId")
	@Mapping(target = "nickname", source = "userReq.nickname")
	@Mapping(target = "profileImageUrl", source = "userReq.profileImageUrl")
	@Mapping(target = "role", source = "role")
	Group.GroupUser toGroupUser(ObjectId userId, GroupRole role, GroupCreateDto.GroupCreateUserReq userReq);
}
