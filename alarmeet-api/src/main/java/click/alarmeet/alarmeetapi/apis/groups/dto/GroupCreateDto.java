package click.alarmeet.alarmeetapi.apis.groups.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public final class GroupCreateDto {
	public record GroupCreateReq(
		@NotNull
		@Valid
		GroupCreateUserReq user,
		@NotNull
		@Valid
		GroupCreateGroupReq group
	) {
	}

	public record GroupCreateUserReq(
		@NotNull
		@Size(min = 1, max = 15)
		String nickname,
		String profileImageUrl
	) {
	}

	public record GroupCreateGroupReq(
		@Size(min = 1, max = 25)
		String name,
		String description,
		String imageUrl
	) {
	}
}
