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
import com.jmp.pmanager.domain.entity.Tarefa;
import com.jmp.pmanager.domain.service.TarefaService;
import com.jmp.pmanager.infrastructure.dto.ProjetoDto;
import com.jmp.pmanager.infrastructure.dto.SalvarProjetoDto;
import com.jmp.pmanager.infrastructure.dto.SalvarTarefaDto;
import com.jmp.pmanager.infrastructure.dto.TarefaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
	private final TarefaService tarefaService;
	
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
	public ResponseEntity<TarefaDto> criarTarefa(@RequestBody @Valid SalvarTarefaDto salvarTarefaDto){
		Tarefa tarefa = tarefaService.criarTarefa(salvarTarefaDto);
		
		return ResponseEntity
				.created(URI.create("/tarefas/" + tarefa.getId()))
				.body(TarefaDto.criar(tarefa));
	}

}
