package click.alarmeet.alarmeetapi.apis.groupinvitecodes.service;

import org.springframework.stereotype.Service;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain.GroupInviteCode;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.repository.GroupInviteCodeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupInviteCodeCreateService {
	private final GroupInviteCodeRepository groupInviteCodeRepository;

	public GroupInviteCode insert(GroupInviteCode groupInviteCode) {
		return groupInviteCodeRepository.insert(groupInviteCode);
	}
}
