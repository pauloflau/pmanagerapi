package com.jmp.pmanager.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.pmanager.domain.entity.Projeto;
import com.jmp.pmanager.domain.model.StatusProjeto;
import com.jmp.pmanager.domain.repository.ProjetoRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarProjetoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;
    
    @Transactional
	public Projeto criarProjeto(SalvarProjetoDto salvarDto) {
		Projeto projeto = Projeto
                .builder()
                .nome(salvarDto.getNome())
                .descricao(salvarDto.getDescricao())
                .dataInicial(salvarDto.getDataInicial())
                .dataFinal(salvarDto.getDataFinal())
                .status(StatusProjeto.PENDENTE)
                .build();

        projetoRepository.save(projeto);

        return projeto;	
	}
}
