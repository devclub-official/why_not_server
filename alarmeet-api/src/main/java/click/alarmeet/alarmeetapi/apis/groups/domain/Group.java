package click.alarmeet.alarmeetapi.apis.groups.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import click.alarmeet.alarmeetapi.apis.groups.constant.GroupRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Group {
	@Id
	private ObjectId id;
	private ObjectId ownerId;
	private String name;
	private String description;
	private String imageUrl;
	private List<GroupUser> users = new ArrayList<>();
	@CreatedDate
	private LocalDateTime createdAt;

	@Builder
	public Group(ObjectId ownerId, String name, String description, String imageUrl, List<GroupUser> users) {
		validate(ownerId, name, users);

		this.ownerId = ownerId;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.users = users;
	}

	private void validate(ObjectId ownerId, String name, List<GroupUser> users) {
		Objects.requireNonNull(ownerId, "Group owner id cannot be null");
		Objects.requireNonNull(name, "Group name cannot be null");
		if (users.isEmpty()) {
			throw new IllegalArgumentException("Group users cannot be empty");
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ToString
	public static class GroupUser {
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
}
