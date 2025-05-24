package click.alarmeet.alarmeetapi.apis.users.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDeleteService {
	private final UserRepository userRepository;

	public void deleteGroupId(ObjectId userId, ObjectId groupId) {
		userRepository.deleteGroupId(userId, groupId);
	}
}
