package com.magalu.comunicados.dto.agendamento;

import java.time.LocalDateTime;

import com.magalu.comunicados.dto.destinatario.DestinatarioSimplificadoDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record AgendamentoSimplificadoDTO(
		@Schema(description = "ID do agendamento.", example = "10")
		Long id,

		@Schema(description = "Data e hora do agendamento.", example = "2025-07-10T21:00:00")
		LocalDateTime dataHora,

		DestinatarioSimplificadoDTO destinatario,

		@Schema(description = "Status do agendamento.", example = "AGUARDANDO_APROVACAO")
		String status,

		@Schema(description = "Mensagem à ser enviada.", example = "Lorem Ipsum")
		String mensagem
) { }
