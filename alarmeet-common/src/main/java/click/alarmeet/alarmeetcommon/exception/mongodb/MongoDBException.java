package click.alarmeet.alarmeetcommon.exception.mongodb;

import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;

public class MongoDBException extends GlobalErrorException {
	public MongoDBException(MongoDBErrorCode errorCode) {
		super(errorCode);
	}
}
