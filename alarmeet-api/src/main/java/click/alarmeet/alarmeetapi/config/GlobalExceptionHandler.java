package click.alarmeet.alarmeetapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import click.alarmeet.alarmeetapi.common.response.ErrorResponse;
import click.alarmeet.alarmeetcommon.exception.GlobalErrorException;
import click.alarmeet.alarmeetcommon.exception.constant.StatusCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
	protected ResponseEntity<ErrorResponse> handleGlobalErrorException(GlobalErrorException e) {
		if (e.getBaseErrorCode().getStatusCode().getCode() >= StatusCode.INTERNAL_SERVER_ERROR.getCode()) {
			// monitoring
		}
		return ResponseEntity.status(e.getStatusCode()).body(ErrorResponse.of(e.getStatusMessage()));
	}

	// BAD_REQUEST
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HandlerMethodValidationException.class)
	protected ErrorResponse handleGlobalErrorException(HandlerMethodValidationException e) {
		return ErrorResponse.of(StatusCode.BAD_REQUEST.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ErrorResponse handleGlobalErrorException(MethodArgumentTypeMismatchException e) {
		return ErrorResponse.of(StatusCode.BAD_REQUEST.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ErrorResponse handleGlobalErrorException(MethodArgumentNotValidException e) {
		return ErrorResponse.of(StatusCode.BAD_REQUEST.getMessage());
	}

	// NOT_FOUND
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoResourceFoundException.class)
	protected ErrorResponse handleGlobalErrorException(NoResourceFoundException e) {
		return ErrorResponse.of(StatusCode.NOT_FOUND.getMessage());
	}

	// METHOD_NOT_ALLOWED
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ErrorResponse handleGlobalErrorException(HttpRequestMethodNotSupportedException e) {
		return ErrorResponse.of(StatusCode.METHOD_NOT_ALLOWED.getMessage());
	}

	// INTERNAL_SERVER_ERROR
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ErrorResponse handleException(Exception e) {
		e.printStackTrace();
		return ErrorResponse.of(StatusCode.INTERNAL_SERVER_ERROR.getMessage());
	}
}
