package click.alarmeet.alarmeetapi.apis.groups.service;

import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupSaveService {
	private final GroupRepository groupRepository;

	public Group create(Group group) {
		return groupRepository.save(group);
	}
}
