package click.alarmeet.alarmeetapi.common.mongodb;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MongoUpdateBuilder {
	/**
	 * Map을 기반으로 Update 객체 생성(null 포함)
	 * updatedAt 자동 할당
	 */
	public Update buildUpdate(Map<String, Object> fields) {
		Update update = new Update();
		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			update.set(entry.getKey(), entry.getValue());
		}

		if (!fields.isEmpty()) {
			update.set("updatedAt", LocalDateTime.now());
		}
		return update;
	}

	/**
	 * Map을 기반으로 Update 객체 생성(null 미포함)
	 * updatedAt 자동 할당
	 */
	public Update buildUpdateWithoutNull(Map<String, Object> fields) {
		Update update = new Update();
		int updatedCount = 0;
		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			if (entry.getValue() != null) {
				update.set(entry.getKey(), entry.getValue());
				updatedCount++;
			}
		}
		if (updatedCount > 0) {
			update.set("updatedAt", LocalDateTime.now());
		}

		return update;
	}
}
