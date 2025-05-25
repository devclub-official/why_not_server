package click.alarmeet.alarmeetapi.apis.groups.repository;

import static click.alarmeet.alarmeetcommon.mongodb.constant.GroupFieldConstants.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.common.mongodb.MongoUpdateBuilder;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {
	private final MongoTemplate mongoTemplate;
	private final MongoUpdateBuilder updateBuilder;

	@Override
	public MongoCountResult update(ObjectId groupId, Map<String, Object> updateFields) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(MONGO_ID).is(groupId)),
			updateBuilder.buildUpdate(updateFields),
			Group.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}

	@Override
	public MongoCountResult addUser(ObjectId groupId, Group.GroupUser groupUser) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(MONGO_ID).is(groupId)),
			new Update().addToSet(USERS, groupUser),
			Group.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}

	@Override
	public Optional<Group.GroupUser> getUser(ObjectId groupId, ObjectId userId) {
		Aggregation aggregation = newAggregation(
			match(where(MONGO_ID).is(groupId)),
			unwind(USERS),
			match(where(USERS_DOT_ID).is(userId)),
			replaceRoot(USERS)
		);

		AggregationResults<Group.GroupUser> result = mongoTemplate.aggregate(
			aggregation,
			COLLECTION_NAME,
			Group.GroupUser.class
		);

		Group.GroupUser user = result.getUniqueMappedResult();
		return Optional.ofNullable(user);
	}
}
