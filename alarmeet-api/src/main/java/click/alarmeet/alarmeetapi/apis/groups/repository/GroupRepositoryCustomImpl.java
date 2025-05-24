package click.alarmeet.alarmeetapi.apis.groups.repository;

import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupFieldConstants.*;
import static click.alarmeet.alarmeetcommon.exception.mongodb.MongoDBErrorCode.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.result.UpdateResult;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.common.mongodb.MongoUpdateBuilder;
import click.alarmeet.alarmeetcommon.exception.mongodb.MongoDBException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {
	private final MongoTemplate mongoTemplate;
	private final MongoUpdateBuilder updateBuilder;

	@Override
	public void updateGroup(ObjectId groupId, Map<String, Object> updateFields) {
		UpdateResult updateResult = mongoTemplate.updateFirst(
			query(where(ID).is(groupId)),
			updateBuilder.buildUpdate(updateFields),
			Group.class
		);
		if (updateResult.getMatchedCount() == 0) {
			throw new MongoDBException(DOCUMENT_NOT_FOUND);
		}
	}
}
