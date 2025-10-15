package com.jmp.pmanager.domain.entity;

import com.jmp.pmanager.domain.model.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id", nullable=false,length=36)
	private String id;
	
	@Column(name="titulo", nullable=false,length=80)
	private String titulo;
	
	@Column(name="descricao", nullable=false,length=150)	
	private String descricao;
	
	@Column(name="numero_de_dias", nullable=false)
	private Integer numeroDeDias;
	
	@Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
	private StatusTarefa status;
	
	@ManyToOne
	@JoinColumn(name="idProjeto")
	private Projeto projeto;
	
    @ManyToOne
    @JoinColumn(name="membroAtribuido")
    private Membro membroAtribuido;
}
