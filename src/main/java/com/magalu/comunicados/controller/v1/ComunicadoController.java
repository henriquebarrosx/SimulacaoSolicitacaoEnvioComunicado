package com.magalu.comunicados.controller.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.dto.ComunicadoIdDTO;
import com.magalu.comunicados.dto.ComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.PaginacaoDTO;
import com.magalu.comunicados.dto.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.dto.TotalPaginacaoDTO;
import com.magalu.comunicados.mapper.ComunicadoMapper;
import com.magalu.comunicados.mapper.PaginacaoMapper;
import com.magalu.comunicados.service.ComunicadoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/comunicados")
@RestController
public class ComunicadoController {

	private final ComunicadoService comunicadoService;
	private final ComunicadoMapper comunicadoMapper;
	private final PaginacaoMapper paginacaoMapper;

	@PostMapping("/agendamentos/solicitar")
	public ResponseEntity<ComunicadoIdDTO> solicitarAgendamento(
			@RequestBody SolicitacaoAgendamentoComunicadoDTO solicitacao) {
		Comunicado comunicado = comunicadoService.cadastrar(solicitacao);
		return ResponseEntity.ok().body(new ComunicadoIdDTO(comunicado.getId()));
	}

	@GetMapping("/agendamentos")
	public ResponseEntity<PaginacaoDTO<ComunicadoSimplificadoDTO>> obterComunidados(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<ComunicadoSimplificadoDTO> comunicados = comunicadoService.obterTodos(page, size)
				.map(comunicadoMapper::toComunicadoSimplificadoDTO);

		var comunicadosPaginados = paginacaoMapper.toPaginacaoDTO(comunicados);
		return ResponseEntity.ok().body(comunicadosPaginados);
	}

	@GetMapping("/agendamentos/{id}")
	public ResponseEntity<ComunicadoSimplificadoDTO> obterComunidado(@PathVariable Long id) {
		Comunicado comunicado = comunicadoService.obterPorId(id);
		ComunicadoSimplificadoDTO comunicadoSimplificado = comunicadoMapper.toComunicadoSimplificadoDTO(comunicado);
		return ResponseEntity.ok().body(comunicadoSimplificado);
	}

}
