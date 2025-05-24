package click.alarmeet.alarmeetapi.apis.users.repository;

import static click.alarmeet.alarmeetcommon.exception.mongodb.MongoDBErrorCode.*;
import static click.alarmeet.alarmeetcommon.mongodb.constant.UserFieldConstants.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetcommon.exception.mongodb.MongoDBException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final MongoTemplate mongoTemplate;

	@Override
	public void addGroupId(ObjectId userId, ObjectId groupId) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(userId)),
			new Update().addToSet(GROUP_IDS, groupId),
			User.class
		);
		if (updateResult.getMatchedCount() == 0) {
			throw new MongoDBException(DOCUMENT_NOT_FOUND);
		}
	}

	public void deleteGroupId(ObjectId userId, ObjectId groupId) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(userId)),
			new Update().pull(GROUP_IDS, groupId),
			User.class
		);
		if (updateResult.getMatchedCount() == 0) {
			throw new MongoDBException(DOCUMENT_NOT_FOUND);
		}
	}
}
