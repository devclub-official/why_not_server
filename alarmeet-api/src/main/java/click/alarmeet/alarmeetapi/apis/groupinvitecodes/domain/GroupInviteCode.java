package click.alarmeet.alarmeetapi.apis.groupinvitecodes.domain;

import static click.alarmeet.alarmeetapi.apis.groupinvitecodes.constant.GroupInviteCodeConstants.*;

import java.time.LocalDateTime;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import click.alarmeet.alarmeetapi.common.util.RandomCodeGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * id: code
 * groupId: unique
 * expiredAt: ttl 설정으로 자동 삭제
 */
@Document(collection = "group_invite_codes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GroupInviteCode {
	@Id
	private String id;
	private ObjectId groupId;
	private LocalDateTime expiredAt;

	private GroupInviteCode(String code, ObjectId groupId, LocalDateTime expiredAt) {
		if (code == null || code.isEmpty()) {
			throw new IllegalArgumentException("code is null or empty");
		}

		this.id = code;
		this.groupId = Objects.requireNonNull(groupId, "groupId is null");
		this.expiredAt = Objects.requireNonNull(expiredAt, "expiredAt is null");
	}

	public static GroupInviteCode createNewWithCode(ObjectId groupId) {
		return new GroupInviteCode(
			RandomCodeGenerator.generateInviteCode(RANDOM_CODE_LENGTH),
			groupId,
			LocalDateTime.now().plusMinutes(EXPIRE_MINUTES)
		);
	}
}
