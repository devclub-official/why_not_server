package click.alarmeet.alarmeetapi.apis.groups.exception;

import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;

public class GroupErrorException extends GlobalErrorException {
	public GroupErrorException(GroupErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
