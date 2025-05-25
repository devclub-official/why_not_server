package click.alarmeet.alarmeetapi.apis.groups.api;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import click.alarmeet.alarmeetapi.apis.groups.dto.GroupMeDto;
import click.alarmeet.alarmeetapi.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group me", description = "그룹에 설정한 프로필 정보 API")
public interface GroupMeApi {
	@Operation(summary = "프로필 조회", description = "그룹에서 설정한 프로필 정보 조회")
	@Parameter(name = "groupId", required = true, in = PATH, schema = @Schema(type = "string"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "프로필 정보"),
		@ApiResponse(responseCode = "404", description = "그룹 또는 그룹 내 유저 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<GroupMeDto.GroupMeRes> getGroupMyProfile(@PathVariable ObjectId groupId);
}
