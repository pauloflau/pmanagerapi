package com.jmp.pmanager.infrastructure.dto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.entity.Projeto;

import static java.util.stream.Collectors.toSet;

import lombok.Data;

@Data
public class MembroDto {
	private final String id;
	private final String secret;
	private final String email;
	private final String nome;
	private final Set<String> idProjeto;
	
	public static MembroDto criar(Membro membro) {
		return new MembroDto(
				membro.getId(),
				membro.getSecret(),
				membro.getNome(),
				membro.getEmail(),
				
			Optional			
				.ofNullable(membro.getProjetos()) 
            	.orElse(List.of())
            	.stream()
            	.map(Projeto::getId) 
            	.collect(toSet())
		);
	}	
}
