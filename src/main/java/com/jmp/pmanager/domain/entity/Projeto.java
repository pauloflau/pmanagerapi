package com.jmp.pmanager.domain.entity;

import java.time.LocalDate;

import com.jmp.pmanager.domain.model.StatusProjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="projeto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id", nullable=false, length = 36)
	private String id;
	
	@Column(name="nome", nullable = false, length = 80)
	private String nome;
	
	@Column(nullable=false, length = 150)
	 private String descricao;

	 @Column(name="data_inicial", nullable=false)
	 private LocalDate dataInicial;

	 @Column(name="data_final", nullable=false)
	 private LocalDate dataFinal;

	 @Column(name="status", nullable = false)
	 @Enumerated(EnumType.STRING)
	 private StatusProjeto status; 	
	
}
