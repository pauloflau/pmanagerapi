package com.jmp.pmanager.infrastructure.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class SalvarProjetoDto {
	
	@NotNull(message = "Nome nao pode ser vazio")
	@Size(min=1, max=50)
	private final String nome;
	
	@NotNull(message = "Descricao nao pode ser vazio")
	@Size(min=1, max=100)	
    private final String descricao;
	
	@NotNull(message = "Data inicial nao pode ser vazio")
    private final LocalDate dataInicial;
	
	@NotNull(message = "Data inicial nao pode ser vazio")
    private final LocalDate dataFinal;
    private final String status;
    
    @AssertTrue(message = "As datas nao sao consistente")
    private boolean isDataInicialAntesDataFinal(){
        return dataInicial.isBefore(dataFinal);
    }
}
