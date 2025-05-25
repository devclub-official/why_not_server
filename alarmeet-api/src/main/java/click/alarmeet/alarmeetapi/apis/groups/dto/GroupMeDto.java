package click.alarmeet.alarmeetapi.apis.groups.dto;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import io.swagger.v3.oas.annotations.media.Schema;

public final class GroupMeDto {
	public record GroupMeRes(
		@Schema(description = "user id", example = "68309e440cff6c5be112952f")
		String id,
		@Schema(description = "해당 그룹 안에서 닉네임", example = "user nickname")
		String nickname,
		@Schema(description = "그룹 안에서 설정한 프로필")
		String profileImageUrl,
		@Schema(description = "그룹 내 역할", examples = {"LEADER", "MANAGER", "MEMBER"})
		GroupRole role
	) {
	}
}
