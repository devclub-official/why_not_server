package click.alarmeet.alarmeetcommon.exception;

import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;

public interface BaseErrorCode {
	StatusCode getStatusCode();

	String getMessage();
}

