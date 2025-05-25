package click.alarmeet.alarmeetapi.apis.groups.mapper;

import java.util.List;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateUserReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto.GroupJoinReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeDto.GroupMeRes;
import click.alarmeet.alarmeetapi.common.mapper.MapStructBaseConfig;

@Mapper(config = MapStructBaseConfig.class)
public interface GroupUserMapper {
	@Mapping(target = "id", source = "userId")
	@Mapping(target = "nickname", source = "userReq.nickname")
	@Mapping(target = "profileImageUrl", source = "userReq.profileImageUrl")
	@Mapping(target = "role", source = "role")
	Group.GroupUser toGroupUser(ObjectId userId, GroupRole role, GroupCreateUserReq userReq);

	List<GroupDetailRes> toGroupDetailRes(List<Group.GroupUser> users);

	@Mapping(target = "id", source = "userId")
	@Mapping(target = "nickname", source = "joinReq.nickname")
	@Mapping(target = "profileImageUrl", source = "joinReq.profileImageUrl")
	@Mapping(target = "role", source = "role")
	Group.GroupUser toGroupUser(ObjectId userId, GroupRole role, GroupJoinReq joinReq);

	GroupMeRes toGroupMeRes(Group.GroupUser groupUser);
}
