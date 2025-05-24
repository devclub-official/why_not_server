package click.alarmeet.alarmeetapi.apis.groups.repository;

import java.util.Map;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetcommon.mongodb.MongoDBException;

public interface GroupRepositoryCustom {
	/**
	 * Map을 통한 값 업데이트
	 *
	 * @param groupId    업데이트할 그룹 ID
	 * @throws MongoDBException 404, DOCUMENT_NOT_FOUND 그룹 존재하지 않는 경우
	 */
	void updateGroup(ObjectId groupId, Map<String, Object> updateFields);
}
