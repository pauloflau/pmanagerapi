package com.jmp.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.pmanager.domain.entity.Projeto;
import com.jmp.pmanager.domain.service.ProjetoService;
import com.jmp.pmanager.infrastructure.dto.ProjetoDto;
import com.jmp.pmanager.infrastructure.dto.SalvarProjetoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("projetos")
@RequiredArgsConstructor
public class ProjetoController {

	private final ProjetoService projetoService;
	
	@PostMapping
	public ResponseEntity<ProjetoDto> criarProjeto(@RequestBody @Valid SalvarProjetoDto dto){
		Projeto projeto = projetoService.criarProjeto(dto);
		return ResponseEntity
				.created(URI.create("/projetos/" + projeto.getId()))
				.body(ProjetoDto.criar(projeto));
	}
}
