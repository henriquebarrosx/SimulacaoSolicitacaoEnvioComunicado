package com.magalu.comunicados.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAgendamentoComunicadoDTO(
		@NotBlank(message = "Data e hora não podem ser vazias") LocalDateTime dataHora,
		@NotNull(message = "Destinatario é obrigatório") DestinatarioDTO destinatario,
		@NotBlank(message = "O conteúdo não pode ser vazio") String mensagem) {
}
