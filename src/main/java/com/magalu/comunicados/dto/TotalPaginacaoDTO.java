package com.magalu.comunicados.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TotalPaginacaoDTO(
		@Schema(description = "Total de páginas", example = "10") int paginas,
		@Schema(description = "Total de resultados paginados", example = "10") Long resultados) {

}
