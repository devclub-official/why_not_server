package click.alarmeet.alarmeetapi.apis.groups.dto;

import static click.alarmeet.alarmeetcommon.mongodb.constant.GroupFieldConstants.*;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public final class GroupUpdateDto {
	public record GroupUpdateReq(
		@NotNull
		@Size(min = 1, max = 25)
		String name,
		@Size(max = 150)
		String description,
		@Size(max = 255)
		String imageUrl
	) {
		public Map<String, Object> toUpdateMap() {
			Map<String, Object> result = new HashMap<>();
			result.put(NAME, this.name);
			result.put(DESCRIPTION, this.description);
			result.put(IMAGE_URL, this.imageUrl);
			return result;
		}
	}
}
