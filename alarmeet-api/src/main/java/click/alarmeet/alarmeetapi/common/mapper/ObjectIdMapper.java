package click.alarmeet.alarmeetapi.common.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.*;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface ObjectIdMapper {
	default String toString(ObjectId id) {
		return id != null ? id.toHexString() : null;
	}

	default ObjectId toObjectId(String id) {
		if (id == null)
			return null;
		if (!ObjectId.isValid(id))
			throw new IllegalArgumentException("invalid ObjectId: " + id);
		return new ObjectId(id);
	}
}
