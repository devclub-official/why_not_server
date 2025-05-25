package click.alarmeet.alarmeetapi.apis.groups.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groups.domain.Group;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListItemRes;
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

	public List<GroupListItemRes> findAll(List<ObjectId> groupIds) {
		return groupRepository.findAllByIdIn(groupIds);
	}

	public Group find(ObjectId id) {
		return groupRepository.findById(id).orElseThrow(() -> new GroupErrorException(GroupErrorCode.GROUP_NOT_FOUND));
	}

	public Group.GroupUser getUser(ObjectId groupId, ObjectId userId) {
		return groupRepository.getUser(groupId, userId)
			.orElseThrow(() -> new GroupErrorException(GroupErrorCode.GROUP_USER_NOT_FOUND));
	}
}
