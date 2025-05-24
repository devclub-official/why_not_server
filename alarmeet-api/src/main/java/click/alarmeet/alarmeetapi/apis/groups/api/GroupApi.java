package click.alarmeet.alarmeetapi.apis.groups.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListRes;
import click.alarmeet.alarmeetapi.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group", description = "group api")
public interface GroupApi {
	@Operation(summary = "그룹 생성 api")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "그룹 생성"),
		@ApiResponse(responseCode = "409", description = "그룹 수 제한 초과", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> createGroup(@RequestBody @Validated GroupCreateDto.GroupCreateReq group);

	@Operation(summary = "그룹 조회 api")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "유저가 참여한 그룹들 반환"),
		@ApiResponse(responseCode = "404", description = "유저 존재하지 않을 떄", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<GroupListRes> getGroups();
}
