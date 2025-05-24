package click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception;

import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;

public class GroupInviteCodeException extends GlobalErrorException {
	public GroupInviteCodeException(GroupInviteCodeErrorCode errorCode) {
		super(errorCode);
	}
}
