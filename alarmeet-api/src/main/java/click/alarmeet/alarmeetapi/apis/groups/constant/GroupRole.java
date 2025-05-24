package click.alarmeet.alarmeetapi.apis.groups.constant;

public enum GroupRole {
	LEADER,
	MANAGER,
	MEMBER;

	public boolean isManagerOrHigher() {
		return this == MANAGER || this == LEADER;
	}
}
