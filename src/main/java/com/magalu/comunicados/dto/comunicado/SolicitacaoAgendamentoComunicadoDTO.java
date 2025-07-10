package com.magalu.comunicados.dto.comunicado;

import java.time.LocalDateTime;

import com.magalu.comunicados.dto.destinatario.DestinatarioDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAgendamentoComunicadoDTO(
		@Schema(description = "Data e hora do comunicado.", example = "2025-07-10T21:00:00") @NotBlank(message = "Data e hora não podem ser vazias") LocalDateTime dataHora,
		@NotNull(message = "Destinatario é obrigatório") DestinatarioDTO destinatario,
		@Schema(description = "Mensagem à ser enviada no comunicado", example = "Lorem Ipsum") @NotBlank(message = "O conteúdo não pode ser vazio") String mensagem) {
}
