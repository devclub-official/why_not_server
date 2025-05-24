package click.alarmeet.alarmeetapi.apis.groups.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class GroupListDto {
	public record GroupListRes(
		List<GroupListItem> groups
	) {
	}

	public record GroupListItem(
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
