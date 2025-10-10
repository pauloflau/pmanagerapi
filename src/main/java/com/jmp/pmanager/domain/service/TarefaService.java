package com.jmp.pmanager.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.pmanager.domain.entity.Tarefa;
import com.jmp.pmanager.domain.model.StatusTarefa;
import com.jmp.pmanager.domain.repository.TarefaRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarTarefaDto;
import com.jmp.pmanager.infrastructure.exception.TarefaDuplicadaException;
import com.jmp.pmanager.infrastructure.exception.TarefaNaoAchadaException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {
	private final TarefaRepository tarefaRepository;
	
	 @Transactional
	    public Tarefa atualizarTarefa(String id, SalvarTarefaDto salvarTarefaDto){
	        if(existeTarefaComMesmoNome(salvarTarefaDto.getTitulo(), id)){
	            throw new TarefaDuplicadaException(salvarTarefaDto.getTitulo());
	        }
	        
	        Tarefa tarefa = buscarId(id);
	        tarefa.setTitulo(salvarTarefaDto.getTitulo());
	        tarefa.setDescricao(salvarTarefaDto.getDescricao());
	        tarefa.setNumeroDeDias(salvarTarefaDto.getNumeroDeDias());
	        tarefa.setStatus(converteParaTarefaStatus(salvarTarefaDto.getStatus()));

	        //nao preciso fazer a linha abaixo pq eu usei a anotacao @Transactional
	        //projetoRepository.save(projeto);
	        
	        addMembrosParaProjeto(salvarProjetoDto.getMembrosIds(), projeto);

	        return tarefa;
	    }

	    //crio metodo para caso crie um status errado
	    private StatusTarefa converteParaProjetoStatus(String statusStr){
	        try{
	            return StatusTarefa.valueOf(statusStr);
	        }catch (IllegalArgumentException | NullPointerException e){
	            throw new TarefaNaoAchadaException(statusStr);
	        }
	    }
	    
	    @Transactional 
	    public void deletarTarefa(String id){
	       Tarefa projeto = buscarId(id);
	       tarefaRepository.delete(projeto);
	    }
	    
	    public Tarefa buscarId(String id) {
	    	return tarefaRepository.findById(id)
	    			.orElseThrow(() -> new TarefaNaoAchadaException(id));
	    }
	    
	public Tarefa criarTarefa(SalvarTarefaDto salvarTarefaDto) {
		Tarefa tarefa = Tarefa
				.builder()
				.titulo(salvarTarefaDto.getTitulo())
				.descricao(salvarTarefaDto.getDescricao())
				.numeroDeDias(salvarTarefaDto.getNumeroDeDias())
				.status(StatusTarefa.PENDENTE)
				.build();
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
}
