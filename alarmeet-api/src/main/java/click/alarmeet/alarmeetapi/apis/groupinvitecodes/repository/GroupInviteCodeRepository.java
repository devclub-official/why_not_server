package click.alarmeet.alarmeetapi.apis.groupinvitecodes.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;

public interface GroupInviteCodeRepository extends MongoRepository<GroupInviteCode, String> {
	Optional<GroupInviteCode> findByGroupId(ObjectId groupId);
}
