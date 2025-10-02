package com.jmp.pmanager.infrastructure.exception;

public class ProjetoDuplicadoException extends RequestException{
    public ProjetoDuplicadoException(String nome) {
        super("Ja existe um projeto com esse nome -> " + nome, "ProjetoDuplicado");
    }
}
