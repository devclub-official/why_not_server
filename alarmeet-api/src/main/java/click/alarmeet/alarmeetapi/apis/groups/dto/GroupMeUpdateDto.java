package click.alarmeet.alarmeetapi.apis.groups.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public final class GroupMeUpdateDto {
	public record GroupMeUpdateReq(
		@Schema(description = "해당 그룹에서 사용할 닉네임", example = "user nickname")
		@NotNull
		@Size(min = 1, max = 15)
		String nickname,
		@Schema(description = "해당 그룹에서 사용할 프로필")
		@Size(max = 255)
		String profileImageUrl
	) {
	}
}
