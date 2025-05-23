package click.alarmeet.alarmeetapi.apis.groups.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import click.alarmeet.alarmeetapi.apis.groups.api.GroupApi;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
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
	@ResponseStatus(HttpStatus.CREATED)
	public void createGroup(@RequestBody @Validated GroupCreateReq group) {
		groupUseCase.createGroup(TEST_ID, group);
	}
}
