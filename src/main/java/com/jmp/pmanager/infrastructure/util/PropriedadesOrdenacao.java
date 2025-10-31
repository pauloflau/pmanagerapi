package com.jmp.pmanager.infrastructure.util;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class PropriedadesOrdenacao {
	
	//defino as propriedades que podem ser definidas
	private static final List<String> PROPRIEDADES_VALIDAS = List.of(
		"titulo", "status", "numero_de_dias"
	);
	
	//variavel que recebe a lista de propriedades que o usuario vai passar
	private final List<String> ListaPropriedadeDeOrdenacao;

	//adiciono o construtor com a lista q o usuario vai passar
	public PropriedadesOrdenacao(String listaSeparadaPorVirgula) {

		ListaPropriedadeDeOrdenacao = Arrays
			.stream(listaSeparadaPorVirgula.split(",")) //separo cada elemento por virgula
			.filter(PROPRIEDADES_VALIDAS::contains)//verifico se esta dentro da propriedade_valida
			.toList();//transformo em lista
	}
}
