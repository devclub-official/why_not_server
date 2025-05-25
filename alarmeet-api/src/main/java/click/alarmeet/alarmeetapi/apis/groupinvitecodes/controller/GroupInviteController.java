package click.alarmeet.alarmeetapi.apis.groupinvitecodes.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.alarmeet.alarmeetapi.apis.groupinvitecodes.api.GroupInviteApi;
import click.alarmeet.alarmeetapi.apis.groupinvitecodes.usecase.GroupInviteUseCase;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupByCodeDto.GroupByCodeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupInviteController implements GroupInviteApi {
	private static final String TEST_ID = "682f51877a2d2278d4d3bf88";
	private static final String TEST_ID_2 = "683204be5d414d8cc0419b72";

	private final GroupInviteUseCase groupInviteUseCase;

	@Override
	@PostMapping("/{groupId}/invite-code")
	public ResponseEntity<GroupInviteCodeDto.GroupInviteCodeRes> createGroupInviteCode(@PathVariable ObjectId groupId) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(groupInviteUseCase.createGroupInviteCode(groupId, TEST_ID));
	}

	@Override
	@GetMapping("/{groupId}/invite-code")
	public ResponseEntity<GroupInviteCodeDto.GroupInviteCodeRes> getGroupInviteCode(@PathVariable ObjectId groupId) {
		return ResponseEntity.ok(groupInviteUseCase.getGroupInviteCode(groupId));
	}

	@Override
	@PostMapping("/join")
	public ResponseEntity<?> joinGroup(@RequestBody @Validated GroupJoinDto.GroupJoinReq req) {
		groupInviteUseCase.joinGroup(TEST_ID_2, req);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	@GetMapping("/join/{code}")
	public ResponseEntity<GroupByCodeRes> getGroupByCode(@PathVariable String code) {
		return ResponseEntity.ok(groupInviteUseCase.getGroupByCode(code));
	}
}
