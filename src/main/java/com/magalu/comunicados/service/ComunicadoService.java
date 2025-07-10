package com.magalu.comunicados.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.domain.entity.Destinatario;
import com.magalu.comunicados.domain.enums.StatusComunicado;
import com.magalu.comunicados.dto.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.exception.NotFoundException;
import com.magalu.comunicados.repository.ComunicadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComunicadoService {

	private final ComunicadoRepository comunicadoRepository;
	private final DestinatarioService destinatarioService;

	public Page<Comunicado> obterTodos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return comunicadoRepository.findAll(pageable);
	}

	public Comunicado obterPorId(Long id) {
		return comunicadoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Comunicado não encontrado com o ID: " + id));
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

	public void remover(Long id) {
		Comunicado comunicado = obterPorId(id);
		comunicadoRepository.delete(comunicado);
	}

}
