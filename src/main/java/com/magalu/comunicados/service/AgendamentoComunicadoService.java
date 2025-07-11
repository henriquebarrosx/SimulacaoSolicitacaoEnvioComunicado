package com.magalu.comunicados.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.AgendamentoComunicado;
import com.magalu.comunicados.domain.entity.Destinatario;
import com.magalu.comunicados.domain.enums.StatusAgendamentoComunicado;
import com.magalu.comunicados.dto.agendamento.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.exception.NotFoundException;
import com.magalu.comunicados.repository.AgendamentoComunicadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgendamentoComunicadoService {

	private final AgendamentoComunicadoRepository agendamentoComunicadoRepository;
	private final DestinatarioService destinatarioService;

	public Page<AgendamentoComunicado> obterTodos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return agendamentoComunicadoRepository.findAll(pageable);
	}

	public AgendamentoComunicado obterPorId(Long id) {
		return agendamentoComunicadoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Agendamento não encontrado com o ID: " + id));
	}

	public AgendamentoComunicado cadastrar(SolicitacaoAgendamentoComunicadoDTO solicitacao) {
		Destinatario destinatario = destinatarioService
				.cadastrar(solicitacao.destinatario().nome(),
						solicitacao.destinatario().email(),
						solicitacao.destinatario().telefone());

		AgendamentoComunicado agendamento = AgendamentoComunicado.builder()
				.dataHoraEnvio(solicitacao.dataHora())
				.destinatario(destinatario)
				.status(StatusAgendamentoComunicado.AGUARDANDO_APROVACAO)
				.mensagem(solicitacao.mensagem())
				.build();

		return agendamentoComunicadoRepository.save(agendamento);
	}

	public void remover(Long id) {
		AgendamentoComunicado comunicado = obterPorId(id);
		agendamentoComunicadoRepository.delete(comunicado);
	}

}
