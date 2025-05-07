package com.onlineshop.exception.handler;

import com.onlineshop.domain.vo.ErrorResponse;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.enums.ArchErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.SocketTimeoutException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String PARAMETRIZED_CONCATENATION = "{}: {}";

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
		AppErrorCode errorCode = ex.getErrorCode();
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorCode.getMessage(),
				errorCode.name());
		log.error("BusinessException: " + PARAMETRIZED_CONCATENATION, errorCode.name(), errorCode.getMessage(), ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(FieldError::getDefaultMessage)
			.toList();

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_002.getReasonPhrase(), errors.toString());
		log.error("Validation error: {}", errors, ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> violations = ex.getConstraintViolations()
			.stream()
			.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
			.toList();

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_013.getReasonPhrase(), violations.toString());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_013.getReasonPhrase(), violations, ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleUncaughtException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				AppErrorCode.ERROR_UNEXPECTED.getMessage(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, AppErrorCode.ERROR_UNEXPECTED.getMessage(), ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_003.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_003.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_004.getReasonPhrase(), ex.getParameterName() + " parameter is missing");
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_004.getReasonPhrase(), ex.getParameterName(), ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
				ArchErrorCode.TECH_006.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_006.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_005.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_005.getReasonPhrase(), ex.getPropertyName(), ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(),
				ArchErrorCode.TECH_007.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_007.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				ArchErrorCode.TECH_008.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_008.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
	}

	@ExceptionHandler(DateTimeParseException.class)
	protected ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ArchErrorCode.TECH_011.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_011.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(SocketTimeoutException.class)
	protected ResponseEntity<ErrorResponse> handleSocketTimeoutException(SocketTimeoutException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.value(),
				ArchErrorCode.TECH_012.getReasonPhrase(), ex.getMessage());
		log.error(PARAMETRIZED_CONCATENATION, ArchErrorCode.TECH_012.getReasonPhrase(), ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(errorResponse);
	}

}
