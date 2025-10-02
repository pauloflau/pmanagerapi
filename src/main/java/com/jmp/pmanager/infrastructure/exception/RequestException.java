package com.jmp.pmanager.infrastructure.exception;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{
	private final String codigoErro;

	public RequestException(String message, String codigoErro) {
		super(message);
		this.codigoErro = codigoErro;
	}
}
