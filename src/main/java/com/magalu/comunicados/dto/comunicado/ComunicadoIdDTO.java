package com.magalu.comunicados.dto.comunicado;

import io.swagger.v3.oas.annotations.media.Schema;

public record ComunicadoIdDTO(@Schema(description = "Id da solicitação", example = "12") Long id) {

}
