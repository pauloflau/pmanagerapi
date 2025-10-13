package com.jmp.pmanager.infrastructure.dto;

import com.jmp.pmanager.domain.model.StatusTarefa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalvarTarefaDto {
	@NotNull(message="Titulo nao pode ser vazio")
	private final String titulo;
	
	@NotNull(message="Descricao nao pode ser vazio")
	@Size(min=3, max=150, message="Descricao invalida")
	private final String descricao;
	
	@NotNull(message="Numero de dias nao pode ser vazio")
	@Positive(message="Numero de dias deve ser positivo")
	private final Integer numeroDeDias;
	
	private final String status;
}
