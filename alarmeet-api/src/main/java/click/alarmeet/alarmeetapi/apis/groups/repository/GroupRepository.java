package click.alarmeet.alarmeetapi.apis.groups.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListItem;

public interface GroupRepository extends MongoRepository<Group, ObjectId> {
	long countByLeaderId(ObjectId leaderId);

	List<GroupListItem> findGroupsByIdIn(List<ObjectId> groupIds);
}
