package com.jmp.pmanager.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="membro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Membro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable=false, length=36)
	private String id;
	
	@Column(nullable=false, length=36)
	private String secret;
	
	@Column(nullable=false, length=80)
	private String nome;
	
	@Column(nullable=false, length=80)
	private String email;
	
	@Column(nullable=false)
	private boolean deleted;
	
    @ManyToMany(mappedBy = "membros")
    private List<Projeto> projetos;
}
