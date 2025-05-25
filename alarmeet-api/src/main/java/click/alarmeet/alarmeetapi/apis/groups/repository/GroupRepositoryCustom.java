package click.alarmeet.alarmeetapi.apis.groups.repository;

import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;

public interface GroupRepositoryCustom {
	/**
	 * Map을 통한 값 업데이트
	 *
	 * @param groupId    업데이트할 그룹 ID
	 * @param updateFields 업데이트 데이터 맵
	 * @return MongoCountResult matchedCount and modifiedCount
	 */
	MongoCountResult update(ObjectId groupId, Map<String, Object> updateFields);

	/**
	 * @return MongoCountResult matchedCount and modifiedCount
	 */
	MongoCountResult addUser(ObjectId groupId, Group.GroupUser groupUser);

	Optional<Group.GroupUser> getUser(ObjectId groupId, ObjectId userId);
}
