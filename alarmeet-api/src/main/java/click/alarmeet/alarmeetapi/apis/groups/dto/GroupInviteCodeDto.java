package click.alarmeet.alarmeetapi.apis.groups.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public final class GroupInviteCodeDto {
	public record GroupInviteCodeRes(
		@Schema(description = "초대코드 8자", example = "Aer2R562")
		String code,
		@Schema(description = "만기 시간")
		LocalDateTime expiredAt
	) {
	}
}
