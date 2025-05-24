package click.alarmeet.alarmeetapi.apis.users.repository;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetcommon.mongodb.MongoDBException;

public interface UserRepositoryCustom {
	/**
	 * user.groupIds에 group id 추가(set)
	 *
	 * @param userId    유저 ID
	 * @param groupId    새로운 그룹 ID
	 * @throws MongoDBException 404, DOCUMENT_NOT_FOUND 유저 존재하지 않는 경우
	 */
	void addGroupId(ObjectId userId, ObjectId groupId);

	/**
	 * user.groupIds에 group id 제거(pull)
	 *
	 * @param userId    유저 ID
	 * @param groupId    삭제할 그룹 ID
	 * @throws MongoDBException 404, DOCUMENT_NOT_FOUND 유저 존재하지 않는 경우
	 */
	void deleteGroupId(ObjectId userId, ObjectId groupId);
}
