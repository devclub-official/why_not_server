package click.alarmeet.alarmeetapi.apis.groups.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupDeleteService {
	private final GroupRepository groupRepository;

	public void deleteGroup(ObjectId groupId, ObjectId leaderId) {
		groupRepository.deleteByIdAndLeaderId(groupId, leaderId);
	}
}
