package com.jmp.pmanager.infrastructure.exception;

public class TarefaStatusInvalidoException  extends RequestException {
	public TarefaStatusInvalidoException(String statusStr) {
		super("Status de tarefa invalido -> " + statusStr, "TarefaStatusInvalido");
    }
}