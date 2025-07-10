package com.magalu.comunicados.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginacaoDTO<T> {

	private @ArraySchema List<T> data;
	private TotalPaginacaoDTO total;
	private @Schema(description = "Total de itens por página", example = "10") int tamanho;
	private @Schema(description = "Índice da página atual", example = "0") int atual;

}
