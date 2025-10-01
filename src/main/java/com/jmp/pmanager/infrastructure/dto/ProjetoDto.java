package com.jmp.pmanager.infrastructure.dto;

import java.time.LocalDate;

import com.jmp.pmanager.domain.entity.Projeto;
import com.jmp.pmanager.domain.model.StatusProjeto;

import lombok.Data;

@Data
public class ProjetoDto {
	private final String id;
	private final String nome;
	private final String descricao;
	private final LocalDate dataInicial;
	private final LocalDate dataFinal;
	private final StatusProjeto status;
	
	public static ProjetoDto criar(Projeto projeto) {
		return new ProjetoDto(
			projeto.getId(),
			projeto.getNome(),
			projeto.getDescricao(),
			projeto.getDataInicial(),
			projeto.getDataFinal(),
			projeto.getStatus()
		);				
	}
}
