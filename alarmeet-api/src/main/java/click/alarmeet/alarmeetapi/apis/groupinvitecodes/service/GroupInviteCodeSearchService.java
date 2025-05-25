package click.alarmeet.alarmeetapi.apis.groupinvitecodes.service;

import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeErrorCode.*;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception.GroupInviteCodeException;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.repository.GroupInviteCodeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupInviteCodeSearchService {
	private final GroupInviteCodeRepository groupInviteCodeRepository;

	public GroupInviteCode find(String code) {
		return groupInviteCodeRepository.findById(code).orElseThrow(() ->
			new GroupInviteCodeException(CODE_NOT_FOUND)
		);
	}

	public GroupInviteCode findByGroupId(ObjectId groupId) {
		return groupInviteCodeRepository.findByGroupId(groupId).orElseThrow(() ->
			new GroupInviteCodeException(GROUP_ID_NOT_FOUND)
		);
	}
}
