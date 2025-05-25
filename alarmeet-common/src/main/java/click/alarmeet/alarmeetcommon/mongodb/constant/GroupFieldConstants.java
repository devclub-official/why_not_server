package click.alarmeet.alarmeetcommon.mongodb.constant;

import static click.alarmeet.alarmeetcommon.mongodb.constant.CommonFieldConstants.*;
import static click.alarmeet.alarmeetcommon.mongodb.constant.GroupUserFieldConstants.*;

public final class GroupFieldConstants {
	public static final String COLLECTION_NAME = "groups";

	public static final String MONGO_ID = "_id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IMAGE_URL = "imageUrl";
	public static final String USERS = "users";

	public static final String USERS_DOT_ID = USERS + DOT + FIELD_ID;

	public static final String USERS_POS_NICKNAME = USERS + DOT + POS + DOT + NICKNAME;
	public static final String USERS_POS_PROFILE_IMAGE_URL = USERS + DOT + POS + DOT + PROFILE_IMAGE_URL;
}
