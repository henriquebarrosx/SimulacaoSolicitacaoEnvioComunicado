package com.magalu.comunicados.dto.comunicado;

import java.time.LocalDateTime;

import com.magalu.comunicados.dto.destinatario.DestinatarioSimplificadoDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa um comunicado com informações essenciais.")
public record ComunicadoSimplificadoDTO(
		@Schema(description = "ID do comunicado.", example = "10") Long id,
		@Schema(description = "Data e hora do comunicado.", example = "2025-07-10T21:00:00") LocalDateTime dataHora,
		DestinatarioSimplificadoDTO destinatario,
		@Schema(description = "Status do comunicado.", example = "AGUARDANDO_APROVACAO") String status) {

}
