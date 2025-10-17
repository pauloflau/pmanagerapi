package com.jmp.pmanager.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.jmp.pmanager.domain.model.StatusProjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projeto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@Column(name = "nome", nullable = false, length = 80)
	private String nome;

	@Column(nullable = false, length = 150)
	private String descricao;

	@Column(name = "data_inicial", nullable = false)
	private LocalDate dataInicial;

	@Column(name = "data_final", nullable = false)
	private LocalDate dataFinal;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusProjeto status;
	
	@ManyToMany
	@JoinTable(
			name="projeto_membro",
			joinColumns = @JoinColumn(name="id_projeto"),
			inverseJoinColumns = @JoinColumn(name="id_membro")
	)
	private List<Membro> membros;

	@OneToMany(mappedBy = "projeto", orphanRemoval = true)
	private List<Tarefa> tarefas;
}
