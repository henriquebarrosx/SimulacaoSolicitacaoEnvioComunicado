package com.magalu.comunicados.mapper;

import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.dto.comunicado.ComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.destinatario.DestinatarioSimplificadoDTO;

@Service
public class ComunicadoMapper {

	public ComunicadoSimplificadoDTO toComunicadoSimplificadoDTO(Comunicado comunicado) {
		DestinatarioSimplificadoDTO destinatario = new DestinatarioSimplificadoDTO(
				comunicado.getDestinatario().getId(),
				comunicado.getDestinatario().getNome(),
				comunicado.getDestinatario().getEmail(),
				comunicado.getDestinatario().getTelefone());

		return new ComunicadoSimplificadoDTO(
				comunicado.getId(),
				comunicado.getDataHora(),
				destinatario,
				comunicado.getStatus().name());
	}

}
