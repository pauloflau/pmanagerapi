package com.jmp.pmanager.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.entity.Projeto;
import com.jmp.pmanager.domain.entity.Tarefa;
import com.jmp.pmanager.domain.model.StatusTarefa;
import com.jmp.pmanager.domain.repository.TarefaRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarTarefaDto;
import com.jmp.pmanager.infrastructure.exception.TarefaNaoAchadaException;
import com.jmp.pmanager.infrastructure.exception.TarefaStatusInvalidoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

	private final ProjetoService projetoService;
	private final MembroService membroService;
	private final TarefaRepository tarefaRepository;

	@Transactional
	public Tarefa atualizarTarefa(String id, SalvarTarefaDto salvarTarefaDto) {
	        
		Tarefa tarefa = buscarId(id);
		tarefa.setTitulo(salvarTarefaDto.getTitulo());
		tarefa.setDescricao(salvarTarefaDto.getDescricao());
		tarefa.setNumeroDeDias(salvarTarefaDto.getNumeroDeDias());
		tarefa.setStatus(converteParaTarefaStatus(salvarTarefaDto.getStatus()));

		// nao preciso fazer a linha abaixo pq eu usei a anotacao @Transactional
		// projetoRepository.save(projeto);

		return tarefa;
	}

	// crio metodo para caso crie um status errado
	private StatusTarefa converteParaTarefaStatus(String statusStr) {
		try {
			return StatusTarefa.valueOf(statusStr);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new TarefaStatusInvalidoException(statusStr);
		}
	}

	@Transactional
	public void deletarTarefa(String id) {
		Tarefa projeto = buscarId(id);
		tarefaRepository.delete(projeto);
	}

	public Tarefa buscarId(String id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoAchadaException(id));
	}

	@Transactional
	public Tarefa criarTarefa(SalvarTarefaDto salvarTarefaDto) {
		
		Projeto projeto = null;
		if( Objects.isNull(salvarTarefaDto.getIdProjeto())){
			//se o projeto veio nulo entao
			projeto = null;
		}else{
			//senão eu busco o id do projeto
			projeto = projetoService.buscarId(salvarTarefaDto.getIdProjeto());
		}
		
		Membro membro = null;
        if(Objects.isNull(salvarTarefaDto.getIdMembro())){
            membro = null;
        }else{
            membro = membroService.buscarMembroId(salvarTarefaDto.getIdMembro());
        }
        
        Tarefa tarefa = Tarefa
        		.builder()
        		.titulo(salvarTarefaDto.getTitulo())
        		.descricao(salvarTarefaDto.getDescricao())
				.numeroDeDias(salvarTarefaDto.getNumeroDeDias())
				.status(StatusTarefa.PENDENTE)
				.projeto(projeto)
				.membroAtribuido(membro)
				.build();

        
        
        
        
		tarefaRepository.save(tarefa);
		return tarefa;
	}
}
