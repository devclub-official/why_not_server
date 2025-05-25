package click.alarmeet.alarmeetapi.apis.groups.repository;

import static click.alarmeet.alarmeetcommon.mongodb.constant.GroupFieldConstants.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
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
			query(where(ID).is(groupId)),
			updateBuilder.buildUpdate(updateFields),
			Group.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}

	@Override
	public MongoCountResult addUser(ObjectId groupId, Group.GroupUser groupUser) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(groupId)),
			new Update().addToSet(USERS, groupUser),
			Group.class
		);

		return MongoCountResult.of(updateResult.getMatchedCount(), updateResult.getModifiedCount());
	}
}
