package click.alarmeet.alarmeetapi.apis.users.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import click.alarmeet.alarmeetapi.apis.users.domain.User;

public interface UserRepository extends MongoRepository<User, ObjectId>, UserRepositoryCustom {
}
