package click.alarmeet.alarmeetapi.apis.users.repository;

import static click.alarmeet.alarmeetcommon.mongodb.constant.UserFieldConstants.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final MongoTemplate mongoTemplate;

	@Override
	public MongoCountResult addGroupId(ObjectId userId, ObjectId groupId) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(userId)),
			new Update().addToSet(GROUP_IDS, groupId),
			User.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}

	@Override
	public MongoCountResult deleteGroupId(ObjectId userId, ObjectId groupId) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(userId)),
			new Update().pull(GROUP_IDS, groupId),
			User.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}
}
