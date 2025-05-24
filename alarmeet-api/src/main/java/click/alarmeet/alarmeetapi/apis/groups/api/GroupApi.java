package click.alarmeet.alarmeetapi.apis.groups.api;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupCreateDto.GroupCreateReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupDetailDto.GroupDetailRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupInviteCodeDto.GroupInviteCodeRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupJoinDto.GroupJoinReq;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupListDto.GroupListRes;
import click.alarmeet.alarmeetapi.apis.groups.dto.GroupUpdateDto.GroupUpdateReq;
import click.alarmeet.alarmeetapi.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group", description = "group api")
public interface GroupApi {
	@Operation(summary = "그룹 생성")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "그룹 생성"),
		@ApiResponse(responseCode = "409", description = "그룹 수 제한 초과", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> createGroup(@RequestBody @Validated GroupCreateReq group);

	@Operation(summary = "그룹 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "유저가 참여한 그룹들 반환"),
		@ApiResponse(responseCode = "404", description = "유저 존재하지 않을 떄", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<GroupListRes> getGroups();

	@Operation(summary = "특정 그룹 조회")
	@Parameter(name = "id", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "해당 그룹 반환"),
		@ApiResponse(responseCode = "403", description = "유저가 해당 그룹에 존재하지 않을 때", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "그룹 존재하지 않을 떄", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	})
	ResponseEntity<GroupDetailRes> getGroup(@PathVariable ObjectId groupId);

	@Operation(summary = "그룹 업데이트")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "그룹 업데이트"),
		@ApiResponse(responseCode = "403", description = "권한 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> updateGroup(@PathVariable ObjectId groupId, @RequestBody @Validated GroupUpdateReq group);

	@Operation(summary = "그룹 삭제")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "그룹 삭제"),
		@ApiResponse(responseCode = "403", description = "권한 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> deleteGroup(@PathVariable ObjectId groupId);

	@Operation(summary = "초대 코드 발급", description = "초대 코드 조회 후 없으면 요청")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "발급한 코드"),
		@ApiResponse(responseCode = "403", description = "권한 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "409", description = "코드 생성 횟수 초과", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupInviteCodeRes> createGroupInviteCode(@PathVariable ObjectId groupId);

	@Operation(summary = "초대 코드 발급", description = "초대 코드 조회")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "발급한 코드"),
		@ApiResponse(responseCode = "404", description = "코드 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupInviteCodeRes> getGroupInviteCode(@PathVariable ObjectId groupId);

	@Operation(summary = "그룹 참가", description = "초대 코드를 통해 그룹 참가")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "그룹 참가"),
		@ApiResponse(responseCode = "404", description = "코드 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "409", description = "참가 최대 4개까지 가능", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<?> joinGroup(@RequestBody @Validated GroupJoinReq group);
}
