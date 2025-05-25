package click.alarmeet.alarmeetapi.apis.groups.dto;

import java.util.List;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import io.swagger.v3.oas.annotations.media.Schema;

public final class GroupDetailDto {
	public record GroupDetailRes(
		GroupDetailGroupRes group,
		List<GroupDetailUserRes> users
	) {
	}

	public record GroupDetailGroupRes(
		@Schema(description = "oid", example = "68309e440cff6c5be112952f")
		String id,
		@Schema(description = "leader id", example = "68309e440cff6c5be112952f")
		String leaderId,
		@Schema(description = "참여 그룹 이름")
		String name,
		@Schema(description = "그룹 설명")
		String description,
		@Schema(description = "image url")
		String imageUrl
	) {
	}

	public record GroupDetailUserRes(
		@Schema(description = "oid", example = "68309e440cff6c5be112952f")
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
