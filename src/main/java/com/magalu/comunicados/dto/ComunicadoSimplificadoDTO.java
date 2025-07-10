package com.magalu.comunicados.dto;

import java.time.LocalDateTime;

public record ComunicadoSimplificadoDTO(Long id, LocalDateTime dataHora, DestinatarioSimplificadoDTO destinatario,
		String status) {

}
