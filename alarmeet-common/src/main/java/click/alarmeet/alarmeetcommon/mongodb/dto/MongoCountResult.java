package click.alarmeet.alarmeetcommon.mongodb.dto;

public record MongoCountResult(
	long matchedCount,
	long modifiedCount
) {
	public static MongoCountResult of(long matchedCount, long modifiedCount) {
		return new MongoCountResult(matchedCount, modifiedCount);
	}

	public boolean isModified() {
		return modifiedCount > 0;
	}

	public boolean isMatched() {
		return matchedCount > 0;
	}
}
