package click.alarmeet.alarmeetapi.apis.groups.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public final class GroupByCodeDto {
	public record GroupByCodeRes(
		GroupByCodeGroupRes group,
		@Schema(description = "만료 시간")
		LocalDateTime expiredAt
	) {
	}

	public record GroupByCodeGroupRes(
		@Schema(description = "oid", example = "68309e440cff6c5be112952f")
		String id,
		@Schema(description = "참여 그룹 이름")
		String name,
		@Schema(description = "그룹 설명")
		String description,
		@Schema(description = "image url")
		String imageUrl
	) {
	}
}
