package click.alarmeet.alarmeetcommon.mongodb;

import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;

public class MongoDBException extends GlobalErrorException {
	public MongoDBException(MongoDBErrorCode errorCode) {
		super(errorCode);
	}
}
