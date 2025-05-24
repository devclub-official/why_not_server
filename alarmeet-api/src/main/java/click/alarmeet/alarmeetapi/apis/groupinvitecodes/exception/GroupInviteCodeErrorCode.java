package click.alarmeet.alarmeetapi.apis.groupinvitecodes.exception;

import click.alarmeet.alarmeetcommon.exception.BaseErrorCode;
import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GroupInviteCodeErrorCode implements BaseErrorCode {
	CODE_NOT_FOUND(StatusCode.NOT_FOUND, "code not found"),
	GROUP_ID_NOT_FOUND(StatusCode.NOT_FOUND, "group id not found"),

	CODE_CREATION_RETRY_EXCEEDED(StatusCode.CONFLICT, "code creation retry exceeded");

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
