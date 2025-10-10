package com.jmp.pmanager.infrastructure.exception;

public class TarefaNaoAchadaException extends RequestException{

	public TarefaNaoAchadaException(String idTarefa) {
		super("Nao foi achado a tarefa: " + idTarefa, " Tarefa nao achado");		
	}
}
