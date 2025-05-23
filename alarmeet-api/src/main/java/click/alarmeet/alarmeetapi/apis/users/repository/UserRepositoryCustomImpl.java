package click.alarmeet.alarmeetapi.apis.users.repository;

import static click.alarmeet.alarmeetcommon.mongodb.MongoDBErrorCode.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetcommon.mongodb.MongoDBException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final MongoTemplate mongoTemplate;

	@Override
	public void addGroupId(ObjectId userId, ObjectId groupId) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where("_id").is(userId)),
			new Update().addToSet("groupIds", groupId),
			User.class
		);
		if (updateResult.getMatchedCount() == 0) {
			throw new MongoDBException(DOCUMENT_NOT_FOUND);
		}
		if (updateResult.getModifiedCount() == 0) {
			throw new MongoDBException(UPDATE_NOT_APPLIED);
		}
	}
}
