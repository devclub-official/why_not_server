package click.alarmeet.alarmeetapi.apis.groups.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;

public interface GroupRepository extends MongoRepository<Group, ObjectId> {
	long countByOwnerId(ObjectId ownerId);
}
