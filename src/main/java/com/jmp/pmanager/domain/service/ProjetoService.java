package com.jmp.pmanager.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.pmanager.domain.entity.Projeto;
import com.jmp.pmanager.domain.model.StatusProjeto;
import com.jmp.pmanager.domain.repository.ProjetoRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarProjetoDto;
import com.jmp.pmanager.infrastructure.exception.ProjetoDuplicadoException;
import com.jmp.pmanager.infrastructure.exception.ProjetoNaoAchadoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;
    
    private boolean existeProjetoComMesmoNome(String nome, String idParaExcluir){
        return projetoRepository
                .findByNome(nome)
                .filter(p -> !Objects.equals(p.getId(), idParaExcluir))
                .isPresent();
    }
    
    @Transactional
    public Projeto atualizarProjeto(String id, SalvarProjetoDto salvarProjetoDto){
        if(existeProjetoComMesmoNome(salvarProjetoDto.getNome(), id)){
            throw new ProjetoDuplicadoException(salvarProjetoDto.getNome());
        }
        
        Projeto projeto = buscarId(id);
        projeto.setNome(salvarProjetoDto.getNome());
        projeto.setDescricao(salvarProjetoDto.getDescricao());
        projeto.setDataInicial(salvarProjetoDto.getDataInicial());
        projeto.setDataFinal(salvarProjetoDto.getDataFinal());
        projeto.setStatus(converteParaProjetoStatus(salvarProjetoDto.getStatus()));

        //nao preciso fazer a linha abaixo pq eu usei a anotacao @Transactional
        //projetoRepository.save(projeto);
        return projeto;
    }

    //crio metodo para caso crie um status errado
    private StatusProjeto converteParaProjetoStatus(String statusStr){
        try{
            return StatusProjeto.valueOf(statusStr);
        }catch (IllegalArgumentException | NullPointerException e){
            throw new ProjetoNaoAchadoException(statusStr);
        }
    }
    
    @Transactional 
    public void deletarProjeto(String id){
       Projeto projeto = buscarId(id);
       projetoRepository.delete(projeto);
    }
    
    public Projeto buscarId(String id) {
    	return projetoRepository.findById(id)
    			.orElseThrow(() -> new ProjetoNaoAchadoException(id));
    }
	@Transactional
	public Projeto criarProjeto(SalvarProjetoDto salvarProjetoDto) {
		if(existeProjetoComMesmoNome(salvarProjetoDto.getNome(), null)){
			throw new ProjetoDuplicadoException(salvarProjetoDto.getNome());
	     }
	     
		Projeto projeto = Projeto
                .builder()
                .nome(salvarProjetoDto.getNome())
                .descricao(salvarProjetoDto.getDescricao())
                .dataInicial(salvarProjetoDto.getDataInicial())
                .dataFinal(salvarProjetoDto.getDataFinal())
                .status(StatusProjeto.PENDENTE)
                .build();

        projetoRepository.save(projeto);

        return projeto;	
	}
}
