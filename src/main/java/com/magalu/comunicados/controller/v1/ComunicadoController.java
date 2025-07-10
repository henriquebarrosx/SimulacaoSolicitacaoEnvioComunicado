package com.magalu.comunicados.controller.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.dto.ComunicadoIdDTO;
import com.magalu.comunicados.dto.ComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.mapper.ComunicadoMapper;
import com.magalu.comunicados.service.ComunicadoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/comunicados")
@RestController
public class ComunicadoController {

	private final ComunicadoService comunicadoService;
	private final ComunicadoMapper comunicadoMapper;

	@PostMapping("/agendamentos/solicitar")
	public ResponseEntity<ComunicadoIdDTO> solicitarAgendamento(
			@RequestBody SolicitacaoAgendamentoComunicadoDTO solicitacao) {
		Comunicado comunicado = comunicadoService.cadastrar(solicitacao);
		return ResponseEntity.ok().body(new ComunicadoIdDTO(comunicado.getId()));
	}

	@GetMapping("/agendamentos")
	public ResponseEntity<List<ComunicadoSimplificadoDTO>> obterComunidados() {
		List<ComunicadoSimplificadoDTO> comunicados = comunicadoService.obterTodos().stream()
				.map(comunicadoMapper::toComunicadoSimplificadoDTO)
				.toList();

		return ResponseEntity.ok().body(comunicados);
	}

}
