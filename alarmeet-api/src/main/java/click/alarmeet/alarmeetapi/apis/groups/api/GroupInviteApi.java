package click.alarmeet.alarmeetapi.apis.groups.api;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupByCodeDto;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto;
import click.alarmeet.alarmeetapi.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group invite", description = "그룹 초대 관련 api")
public interface GroupInviteApi {
	@Operation(summary = "초대 코드 발급", description = "초대 코드 조회 후 없으면 요청")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "발급한 코드"),
		@ApiResponse(responseCode = "403", description = "권한 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "409", description = "코드 생성 횟수 초과", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupInviteCodeDto.GroupInviteCodeRes> createGroupInviteCode(@PathVariable ObjectId groupId);

	@Operation(summary = "초대 코드 발급", description = "초대 코드 조회")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "발급한 코드"),
		@ApiResponse(responseCode = "404", description = "코드 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupInviteCodeDto.GroupInviteCodeRes> getGroupInviteCode(@PathVariable ObjectId groupId);

	@Operation(summary = "그룹 참가", description = "초대 코드를 통해 그룹 참가")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "그룹 참가"),
		@ApiResponse(responseCode = "404", description = "코드 또는 그룹 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "409", description = "참가 최대 4개까지 가능", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> joinGroup(@RequestBody @Validated GroupJoinDto.GroupJoinReq group);

	@Operation(summary = "코드로 그룹 조회", description = "초대 코드로 그룹 및 만료 시간 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "그룹 조회"),
		@ApiResponse(responseCode = "404", description = "코드 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupByCodeDto.GroupByCodeRes> getGroupByCode(@PathVariable String code);
}
