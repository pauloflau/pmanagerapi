package com.jmp.pmanager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmp.pmanager.domain.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String>{

}
