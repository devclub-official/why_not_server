package click.alarmeet.alarmeetapi.apis.groups.service;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupUpdateService {
	private final GroupRepository groupRepository;

	public MongoCountResult addUser(ObjectId groupId, Group.GroupUser groupUser) {
		return groupRepository.addUser(groupId, groupUser);
	}

	public MongoCountResult update(ObjectId groupId, Map<String, Object> updateFields) {
		return groupRepository.update(groupId, updateFields);
	}

	public MongoCountResult updateUser(ObjectId groupId, ObjectId userId, String nickname, String profileImageUrl) {
		return groupRepository.updateUser(groupId, userId, nickname, profileImageUrl);
	}
}
