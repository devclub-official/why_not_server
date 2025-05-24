package click.alarmeet.alarmeetapi.apis.groups.exception;

import click.alarmeet.alarmeetcommon.exception.BaseErrorCode;
import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GroupErrorCode implements BaseErrorCode {
	GROUP_NOT_FOUND(StatusCode.NOT_FOUND, "group not found"),

	GROUP_COUNT_LIMIT_EXCEEDED(StatusCode.CONFLICT, "group count limit exceeded"),

	USER_NOT_IN_GROUP(StatusCode.FORBIDDEN, "user not in group");

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
