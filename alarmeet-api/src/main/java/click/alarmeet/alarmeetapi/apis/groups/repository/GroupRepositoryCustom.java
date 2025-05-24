package click.alarmeet.alarmeetapi.apis.groups.repository;

import java.util.Map;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetcommon.exception.mongodb.MongoDBException;

public interface GroupRepositoryCustom {
	/**
	 * Map을 통한 값 업데이트
	 *
	 * @param groupId    업데이트할 그룹 ID
	 * @param updateFields 업데이트 데이터 맵
	 * @throws MongoDBException 404, DOCUMENT_NOT_FOUND 그룹 존재하지 않는 경우
	 */
	void update(ObjectId groupId, Map<String, Object> updateFields);

	/**
	 * @throws MongoDBException 404, DOCUMENT_NOT_FOUND 그룹 존재하지 않는 경우
	 */
	void addUser(ObjectId groupId, Group.GroupUser groupUser);
}
