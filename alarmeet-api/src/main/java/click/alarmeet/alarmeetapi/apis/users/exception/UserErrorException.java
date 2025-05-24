package click.alarmeet.alarmeetapi.apis.users.exception;

import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;

public class UserErrorException extends GlobalErrorException {
	public UserErrorException(UserErrorCode userErrorCode) {
		super(userErrorCode);
	}
}
