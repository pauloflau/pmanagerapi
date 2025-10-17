package com.jmp.pmanager.infrastructure.dto;

import java.util.Optional;

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
				Optional.ofNullable(tarefa.getProjeto()).map(ProjetoDto::criar).orElse(null),
				Optional.ofNullable(tarefa.getMembroAtribuido()).map(MembroDto::criar).orElse(null)
		);				
	}
}
