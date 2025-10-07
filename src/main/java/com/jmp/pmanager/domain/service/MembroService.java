package com.jmp.pmanager.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jmp.pmanager.domain.entity.Membro;
import com.jmp.pmanager.domain.repository.MembroRepository;
import com.jmp.pmanager.infrastructure.dto.SalvarMembroDto;
import com.jmp.pmanager.infrastructure.exception.MembroDuplicadoException;
import com.jmp.pmanager.infrastructure.exception.MembroNaoAchadoException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembroService {
	private final MembroRepository membroRepository;
	
	@Transactional
	public Membro criarMembro(SalvarMembroDto salvarMembroDto) {
        
		if(existeOutroMembroComEmail(salvarMembroDto.getEmail(), null)){
            throw new MembroDuplicadoException(salvarMembroDto.getEmail());
        }
        
		Membro membro = Membro
				.builder()
				.nome(salvarMembroDto.getNome())
				.email(salvarMembroDto.getEmail())
				.secret(UUID.randomUUID().toString())
				.deleted(false)
				.build();
		
		membroRepository.save(membro);
		return membro;
	}
	
	public Membro buscarMembroId(String idMembro) {
		return membroRepository
				.findByIdAndDeleted(idMembro, false)
				.orElseThrow(() -> new MembroNaoAchadoException(idMembro));				
	}
	
	@Transactional
	public void deletarMembro(String idMembro) {
		Membro membro = buscarMembroId(idMembro);
		membro.setDeleted(true);
	}
	
	@Transactional
	public Membro atualizarMembro(String idMembro, SalvarMembroDto salvarMembroDto) {
		
		if(existeOutroMembroComEmail(salvarMembroDto.getEmail(), idMembro)){
            throw new MembroDuplicadoException(salvarMembroDto.getEmail());
        }
		
		Membro membro = buscarMembroId(idMembro);
		
		membro.setNome(salvarMembroDto.getNome());
		membro.setEmail(salvarMembroDto.getEmail());
		
		return membro;		
	}
	
	private boolean existeOutroMembroComEmail(String email, String idNaoPesquisar) {
		return membroRepository
				//busco um membro nao excluido
				.findByEmailAndDeleted(email, false)
				
				//O .filter(...) ignora o membro que tiver o mesmo ID passado como idNaoPesquisar.
				//ou seja, nao busca pelo email que eu passei
				//e o Objects.equals é usado porque trata nulos com segurança — evita NullPointerException.
				.filter(membro -> !Objects.equals(membro.getId(), idNaoPesquisar))
	                .isPresent();		
	}
	
	public List<Membro> findMembros(String email){
		List<Membro> membros = new ArrayList<Membro>();
		
		if(Objects.isNull(email)) {
			membros = membroRepository.findAllAtivos();
		}else {
			Optional<Membro> membroAchado = membroRepository.findByEmailAndDeleted(email, false);
		
			if(membroAchado.isPresent()) {
				membros.add(membroAchado.get());
			}
		}
		
		return membros;		
	}
}
