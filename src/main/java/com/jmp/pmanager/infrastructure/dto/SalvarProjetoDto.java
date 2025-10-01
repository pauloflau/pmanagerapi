package com.jmp.pmanager.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SalvarProjetoDto {
	private final String nome;
    private final String descricao;
    private final LocalDate dataInicial;
    private final LocalDate dataFinal;
    private final String status;
}
