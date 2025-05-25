package click.alarmeet.alarmeetapi.apis.users.domain;

import static click.alarmeet.alarmeetapi.apis.groups.constant.GroupConstants.*;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {
	@Id
	private ObjectId id;
	private List<ObjectId> groupIds;

	public boolean canJoinGroup(long leaderGroupCount) {
		return groupIds.size() - leaderGroupCount < GROUP_JOIN_MAX_COUNT;
	}

	public boolean isGroupIdExist(ObjectId groupId) {
		return groupIds.stream().anyMatch(id -> id.equals(groupId));
	}
}
