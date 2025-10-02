package com.jmp.pmanager.infrastructure.exception;

public class ProjetoStatusInvalidoException  extends RequestException {
	public ProjetoStatusInvalidoException(String statusStr) {
		super("Status de projeto invalido -> " + statusStr, "ProjetoStatusInvalido");
    }
}