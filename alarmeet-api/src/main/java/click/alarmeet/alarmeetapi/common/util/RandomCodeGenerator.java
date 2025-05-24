package click.alarmeet.alarmeetapi.common.util;

import java.security.SecureRandom;

public class RandomCodeGenerator {
	private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	public static String generateInviteCode(int length) {
		StringBuilder result = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			result.append(CHAR_POOL.charAt(SECURE_RANDOM.nextInt(CHAR_POOL.length())));
		}
		return result.toString();
	}
}
