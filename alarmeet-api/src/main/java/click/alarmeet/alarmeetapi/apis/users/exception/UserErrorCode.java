package click.alarmeet.alarmeetapi.apis.users.exception;

import click.alarmeet.alarmeetcommon.exception.BaseErrorCode;
import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
	USER_NOT_FOUND(StatusCode.NOT_FOUND, "user not found");

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
