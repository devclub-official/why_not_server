package click.alarmeet.alarmeetapi.apis.groups.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public final class GroupCreateDto {
	public record GroupCreateReq(
		@Schema(description = "요청한 유저 그룹 내 프로필 정보")
		@NotNull
		@Valid
		GroupCreateUserReq user,
		@Schema(description = "그룹 정보")
		@NotNull
		@Valid
		GroupCreateGroupReq group
	) {
	}

	public record GroupCreateUserReq(
		@Schema(description = "해당 그룹에서 사용할 닉네임", example = "user nickname")
		@NotNull
		@Size(min = 1, max = 15)
		String nickname,
		@Schema(description = "해당 그룹에서 사용할 프로필")
		@Size(max = 255)
		String profileImageUrl
	) {
	}

	public record GroupCreateGroupReq(
		@Schema(description = "그룹 이름", example = "group name")
		@NotNull
		@Size(min = 1, max = 25)
		String name,
		@Schema(description = "설명", example = "nickname")
		@Size(max = 150)
		String description,
		@Size(max = 255)
		String imageUrl
	) {
	}
}
