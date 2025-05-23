package click.alarmeet.alarmeetapi.apis.groups.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.domain.GroupUser;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateGroupReq;
import click.alarmeet.alarmeetapi.config.MapStructBaseConfig;

@Mapper(
	config = MapStructBaseConfig.class,
	uses = {GroupUserMapper.class}
)
public interface GroupMapper {
	@Mapping(target = "ownerId", source = "userId")
	@Mapping(target = "name", source = "groupReq.name")
	@Mapping(target = "description", source = "groupReq.description")
	@Mapping(target = "imageUrl", source = "groupReq.imageUrl")
	@Mapping(target = "users", source = "groupUser", qualifiedByName = "groupUserToGroupUsers")
	Group toGroup(String userId, GroupCreateGroupReq groupReq, GroupUser groupUser);

	@Named("groupUserToGroupUsers")
	default List<GroupUser> toGroupUsers(
		GroupUser groupUser
	) {
		return List.of(groupUser);
	}
}
