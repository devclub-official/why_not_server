package click.alarmeet.alarmeetapi.apis.users.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.users.domain.User;
import click.alarmeet.alarmeetapi.apis.users.exception.UserErrorCode;
import click.alarmeet.alarmeetapi.apis.users.exception.UserErrorException;
import click.alarmeet.alarmeetapi.apis.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSearchService {
	private final UserRepository userRepository;

	public User findUser(ObjectId id) {
		return userRepository.findById(id).orElseThrow(() -> new UserErrorException(UserErrorCode.USER_NOT_FOUND));
	}
}
