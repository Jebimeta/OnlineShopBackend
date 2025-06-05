package com.onlineshop.exception;

import com.onlineshop.exception.enums.AppErrorCode;
import lombok.Getter;

//// Clase que representa una excepción de negocio personalizada para la aplicación.
@Getter
public class BusinessException extends RuntimeException {

	private final AppErrorCode errorCode;

	/**
	 * Constructor de la excepción de negocio.
	 * @param errorCode
	 */
	public BusinessException(AppErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	/**
	 * Constructor de la excepción de negocio con causa.
	 * @param errorCode
	 * @param cause
	 */
	public BusinessException(AppErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}

}
