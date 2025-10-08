package com.jmp.pmanager.infrastructure.dto;

import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jmp.pmanager.domain.entity.Membro;
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
	private final Set<String> idMembro;
	
	public static ProjetoDto criar(Projeto projeto) {
		return new ProjetoDto(
			projeto.getId(),
			projeto.getNome(),
			projeto.getDescricao(),
			projeto.getDataInicial(),
			projeto.getDataFinal(),
			projeto.getStatus(),
			
			Optional			
			.ofNullable(projeto.getMembros()) 
        	.orElse(List.of())
        	.stream()
        	.map(Membro::getId) 
        	.collect(toSet())
		);				
	}
}
