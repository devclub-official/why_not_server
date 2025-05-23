package click.alarmeet.alarmeetapi.apis.groups.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
