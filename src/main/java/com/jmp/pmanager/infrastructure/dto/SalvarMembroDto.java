package com.jmp.pmanager.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalvarMembroDto {

	@NotNull(message = "Nome nao pode ser vazio")
	@Size(min=1, max=50, message="Membro com nome invalido")
	private final String nome;
	
	@NotNull(message = "Email nao pode ser vazio")
	@Email(message = "Email nao e valido")
    private final String email;
		
}
