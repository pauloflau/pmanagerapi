package com.jmp.pmanager.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestError {
	private final String codigoErro;
	private final String mensagemErro;
	private final int status;
	private final String path;
}
