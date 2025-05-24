package click.alarmeet.alarmeetapi.apis.groups.controller;

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

import click.alarmeet.alarmeetapi.apis.groups.api.GroupApi;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListRes;
import click.alarmeet.alarmeetapi.apis.groups.usecase.GroupUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController implements GroupApi {
	private static final String TEST_ID = "682f51877a2d2278d4d3bf88";

	private final GroupUseCase groupUseCase;

	@PostMapping
	public ResponseEntity<?> createGroup(@RequestBody @Validated GroupCreateReq group) {
		groupUseCase.createGroup(TEST_ID, group);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<GroupListRes> getGroups() {
		return ResponseEntity.ok(groupUseCase.getGroups(TEST_ID));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupDetailRes> getGroup(@PathVariable ObjectId id) {
		return ResponseEntity.ok(groupUseCase.getGroup(TEST_ID, id));
	}
}
