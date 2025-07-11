package com.magalu.comunicados.mapper;

import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.AgendamentoComunicado;
import com.magalu.comunicados.dto.agendamento.AgendamentoSimplificadoDTO;
import com.magalu.comunicados.dto.destinatario.DestinatarioSimplificadoDTO;

@Service
public class AgendamentoComunicadoMapper {

	public AgendamentoSimplificadoDTO toAgendamentoSimplificado(AgendamentoComunicado agendamento) {
		DestinatarioSimplificadoDTO destinatario = new DestinatarioSimplificadoDTO(
				agendamento.getDestinatario().getId(),
				agendamento.getDestinatario().getNome(),
				agendamento.getDestinatario().getEmail(),
				agendamento.getDestinatario().getTelefone());

		return new AgendamentoSimplificadoDTO(
				agendamento.getId(),
				agendamento.getDataHoraEnvio(),
				destinatario,
				agendamento.getStatus().name(),
				agendamento.getMensagem());
	}

}
