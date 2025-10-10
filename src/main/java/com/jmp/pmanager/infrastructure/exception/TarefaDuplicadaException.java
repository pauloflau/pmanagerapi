package com.jmp.pmanager.infrastructure.exception;

public class TarefaDuplicadaException extends RequestException{
    public TarefaDuplicadaException(String nome) {
        super("Ja existe uma tarefa com esse nome -> " + nome, "TarefaDuplicado");
    }
}
