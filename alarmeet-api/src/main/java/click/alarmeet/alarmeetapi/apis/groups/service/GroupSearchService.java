package click.alarmeet.alarmeetapi.apis.groups.service;

import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupSearchService {
	private final GroupRepository groupRepository;
	
	public long countByOwnerId(String ownerId) {
		return groupRepository.countByOwnerId(ownerId);
	}
}
