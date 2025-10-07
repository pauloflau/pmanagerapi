package com.jmp.pmanager.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jmp.pmanager.domain.entity.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, String>{
	Optional<Membro> findByIdAndDeleted(String id, boolean deleted);
	
	Optional<Membro> findByEmailAndDeleted(String email, boolean deleted);
	
    @Query("SELECT m FROM Membro m WHERE m.deleted = false")
    List<Membro> findAllAtivos();
}
