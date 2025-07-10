package com.magalu.comunicados.dto.comunicado;

import java.time.LocalDateTime;

import com.magalu.comunicados.dto.destinatario.DestinatarioSimplificadoDTO;

public record ComunicadoSimplificadoDTO(Long id, LocalDateTime dataHora, DestinatarioSimplificadoDTO destinatario,
		String status) {

}
