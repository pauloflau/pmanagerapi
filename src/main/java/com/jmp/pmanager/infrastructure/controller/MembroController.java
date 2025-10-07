package com.jmp.pmanager.infrastructure.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/{idMembro}")
	public ResponseEntity<MembroDto> buscarMembroId(@PathVariable String idMembro){
		Membro membro = membroService.buscarMembroId(idMembro);
		return ResponseEntity.ok(MembroDto.criar(membro));
	}
	
	@DeleteMapping("/{idMembro}")
	public ResponseEntity<Void> deletarMembroId(@PathVariable String idMembro){
		membroService.deletarMembro(idMembro);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{idMembro}")
	public ResponseEntity<MembroDto> atualizarMembro(
			@PathVariable String idMembro,
			@RequestBody SalvarMembroDto salvarMembroDto) {
		
		Membro membro = membroService.atualizarMembro(idMembro, salvarMembroDto);
		return ResponseEntity.ok(MembroDto.criar(membro));		
	}
	
	@GetMapping
	public ResponseEntity<List<MembroDto>> buscarMembros(
			@RequestParam(required=false) String email){
		List<Membro> membros = membroService.findMembros(email);
		return ResponseEntity.ok(membros.stream().map(MembroDto::criar).toList());
	}
	
}
