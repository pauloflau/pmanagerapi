package com.jmp.pmanager.infrastructure.exception;

public class ProjetoNaoAchadoException extends RequestException{

	public ProjetoNaoAchadoException(String idProjeto) {
		super("Nao foi achado o projeto: " + idProjeto, " Projeto nao achado");		
	}
}
