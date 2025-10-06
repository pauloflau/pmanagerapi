package com.jmp.pmanager.infrastructure.exception;

public class MembroNaoAchadoException extends RequestException{

	public MembroNaoAchadoException(String idMembro) {
		super("Nao foi achado o membro: " + idMembro, " membro nao achado");		
	}

}
