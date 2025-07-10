package com.magalu.comunicados.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.domain.entity.Destinatario;
import com.magalu.comunicados.domain.enums.StatusComunicado;
import com.magalu.comunicados.dto.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.repository.ComunicadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComunicadoService {

	private final ComunicadoRepository comunicadoRepository;
	private final DestinatarioService destinatarioService;

	public List<Comunicado> obterTodos() {
		return comunicadoRepository.findAll();
	}

	public Comunicado cadastrar(SolicitacaoAgendamentoComunicadoDTO solicitacao) {
		Destinatario destinatario = destinatarioService
				.cadastrar(solicitacao.destinatario().nome(),
						solicitacao.destinatario().email(),
						solicitacao.destinatario().telefone());

		Comunicado comunicado = Comunicado.builder()
				.dataHora(solicitacao.dataHora())
				.destinatario(destinatario)
				.status(StatusComunicado.AGUARDANDO_APROVACAO)
				.build();

		return comunicadoRepository.save(comunicado);
	}

}
