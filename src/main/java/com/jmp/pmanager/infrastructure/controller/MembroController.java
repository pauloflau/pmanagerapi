package com.jmp.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.service.MembroService;
import com.jmp.pmanager.infrastructure.dto.MembroDto;
import com.jmp.pmanager.infrastructure.dto.SalvarMembroDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/membro")
@RequiredArgsConstructor
public class MembroController {

	private final MembroService membroService;
		
	@PostMapping
	public ResponseEntity<MembroDto> criarMembro(@RequestBody @Valid SalvarMembroDto dto){
		Membro membro = membroService.criarMembro(dto);
		return ResponseEntity
				.created(URI.create("/projetos/" + membro.getId()))
				.body(MembroDto.criar(membro));
	}	
}
