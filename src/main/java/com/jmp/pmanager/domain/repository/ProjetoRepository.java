package com.jmp.pmanager.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmp.pmanager.domain.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, String>{
	Optional<Projeto> findByNome(String nome);
}
