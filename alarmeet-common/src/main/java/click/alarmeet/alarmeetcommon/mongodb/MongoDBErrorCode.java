package click.alarmeet.alarmeetcommon.mongodb;

import click.alarmeet.alarmeetcommon.exception.BaseErrorCode;
import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MongoDBErrorCode implements BaseErrorCode {
	DOCUMENT_NOT_FOUND(StatusCode.NOT_FOUND, "document not found"),
	UPDATE_NOT_APPLIED(StatusCode.CONFLICT, "update not applied"),
	;

	private final StatusCode statusCode;
	private final String message;

	@Override
	public StatusCode getStatusCode() {
		return statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
