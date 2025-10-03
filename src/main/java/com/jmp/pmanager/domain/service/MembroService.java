package com.jmp.pmanager.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.repository.MembroRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarMembroDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembroService {
	private final MembroRepository membroRepository;
	
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
}
