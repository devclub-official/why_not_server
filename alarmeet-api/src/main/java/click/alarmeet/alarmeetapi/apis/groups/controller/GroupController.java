package click.alarmeet.alarmeetapi.apis.groups.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.alarmeet.alarmeetapi.apis.groups.api.GroupApi;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto.GroupInviteCodeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto.GroupJoinReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupUpdateDto.GroupUpdateReq;
import click.alarmeet.alarmeetapi.apis.groups.usecase.GroupUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController implements GroupApi {
	private static final String TEST_ID = "682f51877a2d2278d4d3bf88";
	private static final String TEST_ID_2 = "683204be5d414d8cc0419b72";

	private final GroupUseCase groupUseCase;

	@Override
	@PostMapping
	public ResponseEntity<?> createGroup(@RequestBody @Validated GroupCreateReq req) {
		groupUseCase.createGroup(TEST_ID, req);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	@GetMapping
	public ResponseEntity<GroupListRes> getGroups() {
		return ResponseEntity.ok(groupUseCase.getGroups(TEST_ID));
	}

	@Override
	@GetMapping("/{groupId}")
	public ResponseEntity<GroupDetailRes> getGroup(@PathVariable ObjectId groupId) {
		return ResponseEntity.ok(groupUseCase.getGroup(groupId, TEST_ID));
	}

	@Override
	@PutMapping("/{groupId}")
	public ResponseEntity<?> updateGroup(@PathVariable ObjectId groupId, @RequestBody @Validated GroupUpdateReq req) {
		groupUseCase.updateGroup(groupId, TEST_ID, req);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{groupId}")
	public ResponseEntity<?> deleteGroup(@PathVariable ObjectId groupId) {
		groupUseCase.deleteGroup(groupId, TEST_ID);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PostMapping("/{groupId}/invite-code")
	public ResponseEntity<GroupInviteCodeRes> createGroupInviteCode(@PathVariable ObjectId groupId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(groupUseCase.createGroupInviteCode(groupId, TEST_ID));
	}

	@Override
	@GetMapping("/{groupId}/invite-code")
	public ResponseEntity<GroupInviteCodeRes> getGroupInviteCode(@PathVariable ObjectId groupId) {
		return ResponseEntity.ok(groupUseCase.getGroupInviteCode(groupId));
	}

	@Override
	@PostMapping("/join")
	public ResponseEntity<?> joinGroup(@RequestBody @Validated GroupJoinReq req) {
		groupUseCase.joinGroup(TEST_ID_2, req);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
