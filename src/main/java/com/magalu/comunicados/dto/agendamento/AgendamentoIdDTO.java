package com.magalu.comunicados.dto.agendamento;

import io.swagger.v3.oas.annotations.media.Schema;

public record AgendamentoIdDTO(
        @Schema(description = "Id do agendamento", example = "12")
        Long id
) { }
