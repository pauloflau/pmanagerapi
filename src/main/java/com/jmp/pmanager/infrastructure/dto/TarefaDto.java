package com.jmp.pmanager.infrastructure.dto;

import com.jmp.pmanager.domain.entity.Tarefa;
import com.jmp.pmanager.domain.model.StatusTarefa;

import lombok.Data;

@Data
public class TarefaDto {
	private final String id;
	private final String titulo;
	private final String descricao;
	private final Integer numeroDeDias;
	private final StatusTarefa status;
	private final ProjetoDto projeto;
	private final MembroDto membroAtribuido;
	
	public static TarefaDto criar(Tarefa tarefa) {
		return new TarefaDto(
				tarefa.getId(),
				tarefa.getTitulo(),
				tarefa.getDescricao(),
				tarefa.getNumeroDeDias(),
				tarefa.getStatus(),
				ProjetoDto.criar(tarefa.getProjeto()),
				MembroDto.criar(tarefa.getMembroAtribuido())
		);				
	}
}
