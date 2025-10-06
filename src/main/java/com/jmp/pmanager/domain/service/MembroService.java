package com.jmp.pmanager.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.repository.MembroRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarMembroDto;
import com.jmp.pmanager.infrastructure.exception.MembroNaoAchadoException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembroService {
	private final MembroRepository membroRepository;
	
	@Transactional
	public Membro criarMembro(SalvarMembroDto salvarMembroDto) {
		Membro membro = Membro
				.builder()
				.nome(salvarMembroDto.getNome())
				.email(salvarMembroDto.getEmail())
				.secret(UUID.randomUUID().toString())
				.deleted(false)
				.build();
		
		membroRepository.save(membro);
		return membro;
	}
	
	public Membro buscarMembroId(String idMembro) {
		return membroRepository
				.findByIdAndDeleted(idMembro, false)
				.orElseThrow(() -> new MembroNaoAchadoException(idMembro));				
	}
	
	@Transactional
	public void deletarMembro(String idMembro) {
		Membro membro = buscarMembroId(idMembro);
		membro.setDeleted(true);
	}
	
	@Transactional
	public Membro atualizarMembro(String idMembro, SalvarMembroDto salvarMembroDto) {
		Membro membro = buscarMembroId(idMembro);
		
		membro.setNome(salvarMembroDto.getNome());
		membro.setEmail(salvarMembroDto.getEmail());
		
		return membro;		
	}
}
