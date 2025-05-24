package click.alarmeet.alarmeetapi.apis.groups.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorCode;
import click.alarmeet.alarmeetapi.apis.groups.exception.GroupErrorException;
import click.alarmeet.alarmeetapi.apis.groups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupSearchService {
	private final GroupRepository groupRepository;

	public long countByLeaderId(ObjectId leaderId) {
		return groupRepository.countByLeaderId(leaderId);
	}

	public List<GroupListDto.GroupListItem> findGroups(List<ObjectId> groupIds) {
		return groupRepository.findGroupsByIdIn(groupIds);
	}

	public Group findGroup(ObjectId id) {
		return groupRepository.findById(id).orElseThrow(() -> new GroupErrorException(GroupErrorCode.GROUP_NOT_FOUND));
	}
}
