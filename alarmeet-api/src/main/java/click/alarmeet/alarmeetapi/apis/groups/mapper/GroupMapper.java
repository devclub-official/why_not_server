package click.alarmeet.alarmeetapi.apis.groups.mapper;

import java.util.List;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateGroupReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.common.mapper.MapStructBaseConfig;

@Mapper(
	config = MapStructBaseConfig.class,
	uses = {GroupUserMapper.class}
)
public interface GroupMapper {
	@Mapping(target = "leaderId", source = "userId")
	@Mapping(target = "name", source = "groupReq.name")
	@Mapping(target = "description", source = "groupReq.description")
	@Mapping(target = "imageUrl", source = "groupReq.imageUrl")
	@Mapping(target = "users", source = "groupUser", qualifiedByName = "groupUserToGroupUsers")
	Group toGroup(ObjectId userId, GroupCreateGroupReq groupReq, Group.GroupUser groupUser);

	@Named("groupUserToGroupUsers")
	default List<Group.GroupUser> toGroupUsers(
		Group.GroupUser groupUser
	) {
		return List.of(groupUser);
	}

	@Mapping(target = "group.id", source = "id")
	@Mapping(target = "group.leaderId", source = "leaderId")
	@Mapping(target = "group.name", source = "name")
	@Mapping(target = "group.description", source = "description")
	@Mapping(target = "group.imageUrl", source = "imageUrl")
	@Mapping(target = "users", source = "users")
	GroupDetailRes toGroupDetailRes(Group group);
}
