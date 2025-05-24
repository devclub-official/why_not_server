package click.alarmeet.alarmeetapi.apis.groups.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto;
import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupSearchService {
	private final GroupRepository groupRepository;

	public long countByOwnerId(ObjectId ownerId) {
		return groupRepository.countByOwnerId(ownerId);
	}

	public List<GroupListDto.GroupListItem> findGroups(List<ObjectId> groupIds) {
		return groupRepository.findGroupsByIdIn(groupIds);
	}
}
