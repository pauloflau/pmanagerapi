package com.jmp.pmanager.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jmp.pmanager.domain.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String> {

	@Query(
		"""
		SELECT t
		FROM Tarefa t
		WHERE
		    (:idProjeto IS NULL OR t.projeto.id= :idProjeto) AND
		    (:idMembro IS NULL OR t.membroAtribuido.id= :idMembro) AND
		    (:status IS NULL OR t.status= :status) AND
		    (:tituloParcial IS NULL OR UPPER(t.titulo) LIKE CONCAT ('%', UPPER(:tituloParcial), '%'))
		""")
	List<Tarefa> buscarTarefa(
		@Param("idProjeto") String idProjeto, 
		@Param("idMembro") String idMembro, 
		@Param("status") String status, 
		@Param("tituloParcial") String tituloParcial
		//Pageable pageable
	);
}
