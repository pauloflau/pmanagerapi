package com.jmp.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("{id}")
	public ResponseEntity<ProjetoDto> atualizarProjeto(
		@PathVariable String id,  
		@RequestBody @Valid SalvarProjetoDto dto
	){
		Projeto projeto = projetoService.atualizarProjeto(id, dto);
		return ResponseEntity.ok(ProjetoDto.criar(projeto));
	}
	 
	@DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable String id){
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
	}
    
        @GetMapping("/{id}")
    public ResponseEntity<ProjetoDto> findById(@PathVariable String id){
        Projeto projeto = projetoService.buscarId(id);
        return  ResponseEntity.ok(ProjetoDto.criar(projeto)); 
    }
    
	@PostMapping
	public ResponseEntity<ProjetoDto> criarProjeto(@RequestBody @Valid SalvarProjetoDto dto){
		Projeto projeto = projetoService.criarProjeto(dto);
		return ResponseEntity
				.created(URI.create("/projetos/" + projeto.getId()))
				.body(ProjetoDto.criar(projeto));
	}
}
