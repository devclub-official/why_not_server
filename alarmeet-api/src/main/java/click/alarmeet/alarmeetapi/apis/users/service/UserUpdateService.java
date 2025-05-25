package click.alarmeet.alarmeetapi.apis.users.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.users.repository.UserRepository;
import click.alarmeet.alarmeetcommon.mongodb.dto.MongoCountResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUpdateService {
	private final UserRepository userRepository;

	public MongoCountResult addGroupId(ObjectId userId, ObjectId groupId) {
		return userRepository.addGroupId(userId, groupId);
	}
}
