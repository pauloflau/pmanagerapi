package com.jmp.pmanager.infrastructure.dto;

import com.jmp.pmanager.domain.entity.Membro;

import lombok.Data;

@Data
public class MembroDto {
	private final String id;
	private final String secret;
	private final String email;
	private final String nome;
	
	public static MembroDto criar(Membro membro) {
		return new MembroDto(
				membro.getId(),
				membro.getSecret(),
				membro.getNome(),
				membro.getEmail()
		);
	}	
}
