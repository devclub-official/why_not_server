package click.alarmeet.alarmeetapi.apis.groups.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import org.bson.types.ObjectId;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GroupUser {
	private ObjectId id;
	private String nickname;
	private String profileImageUrl;
	private GroupRole role;
	private LocalDateTime joinedAt;

	@Builder
	public GroupUser(ObjectId id, String nickname, String profileImageUrl, GroupRole role) {
		validate(id, nickname, role);

		this.id = id;
		this.nickname = nickname;
		this.profileImageUrl = profileImageUrl;
		this.role = role;
		this.joinedAt = LocalDateTime.now();
	}

	private void validate(ObjectId id, String nickname, GroupRole role) {
		Objects.requireNonNull(id, "Group user id cannot be null");
		Objects.requireNonNull(nickname, "Group user nickname cannot be null");
		Objects.requireNonNull(role, "Group user role cannot be null");
	}
}
