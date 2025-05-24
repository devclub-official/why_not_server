package click.alarmeet.alarmeetapi.apis.users.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUpdateService {
	private final UserRepository userRepository;

	public void addGroupId(ObjectId userId, ObjectId groupId) {
		userRepository.addGroupId(userId, groupId);
	}
}
