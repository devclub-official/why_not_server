package click.alarmeet.alarmeetapi.apis.groups.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.alarmeet.alarmeetapi.apis.groups.api.GroupMeApi;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeDto.GroupMeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeUpdateDto.GroupMeUpdateReq;
import click.alarmeet.alarmeetapi.apis.groups.usecase.GroupMeUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups/{groupId}/me")
public class GroupMeController implements GroupMeApi {
	private static final String TEST_ID = "682f51877a2d2278d4d3bf88";

	private final GroupMeUseCase groupMeUseCase;

	@Override
	@GetMapping
	public ResponseEntity<GroupMeRes> getGroupMyProfile(@PathVariable ObjectId groupId) {
		return ResponseEntity.ok(groupMeUseCase.getGroupUser(groupId, TEST_ID));
	}

	@Override
	@PutMapping
	public ResponseEntity<?> updateGroupUser(
		@PathVariable ObjectId groupId,
		@RequestBody @Validated GroupMeUpdateReq req
	) {
		groupMeUseCase.updateGroupUser(groupId, TEST_ID, req);
		return ResponseEntity.noContent().build();
	}
}
