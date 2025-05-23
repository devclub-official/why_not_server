package click.alarmeet.alarmeetapi.apis.groups.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group", description = "group api")
public interface GroupApi {
	@Operation(summary = "그룹 생성 api")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "그룹 생성")
	})
	void createGroup(@RequestBody @Validated GroupCreateDto.GroupCreateReq group);
}
