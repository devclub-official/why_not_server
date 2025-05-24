package click.alarmeet.alarmeetapi.apis.groups.service;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupUpdateService {
	private final GroupRepository groupRepository;

	public void updateGroup(ObjectId groupId, Map<String, Object> updateFields) {
		groupRepository.updateGroup(groupId, updateFields);
	}
}
