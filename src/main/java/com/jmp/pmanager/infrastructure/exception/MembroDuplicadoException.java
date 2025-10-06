package com.jmp.pmanager.infrastructure.exception;

public class MembroDuplicadoException extends RequestException{
    public MembroDuplicadoException(String email){
        super("MembroDuplicado", "Um membro com o esse email ja existe: " + email);
    }
}
