package com.magalu.comunicados.controller.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magalu.comunicados.domain.entity.Comunicado;
import com.magalu.comunicados.dto.PaginacaoDTO;
import com.magalu.comunicados.dto.comunicado.ComunicadoIdDTO;
import com.magalu.comunicados.dto.comunicado.ComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.comunicado.PaginacaoComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.comunicado.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.mapper.ComunicadoMapper;
import com.magalu.comunicados.mapper.PaginacaoMapper;
import com.magalu.comunicados.service.ComunicadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Comunicados (v1)", description = "Endpoints para gerenciamento de comunicados")
@RequiredArgsConstructor
@RequestMapping("/v1/comunicados")
@RestController
public class V1SolicitacaoComunicadoController {

	private final ComunicadoService comunicadoService;
	private final ComunicadoMapper comunicadoMapper;
	private final PaginacaoMapper paginacaoMapper;

	@PostMapping("/agendamentos/solicitar")
	public ResponseEntity<ComunicadoIdDTO> solicitarAgendamento(
			@RequestBody SolicitacaoAgendamentoComunicadoDTO solicitacao) {
		Comunicado comunicado = comunicadoService.cadastrar(solicitacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ComunicadoIdDTO(comunicado.getId()));
	}

	@Operation(description = "Retorna uma lista com todas as solicitações de agendamento para envio de comunicados de forma paginada")
	@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginacaoComunicadoSimplificadoDTO.class)))
	@GetMapping("/agendamentos")
	public ResponseEntity<PaginacaoDTO<ComunicadoSimplificadoDTO>> obterComunidados(
			@Parameter(description = "Número da pagina à carregar", required = true) @RequestParam(defaultValue = "0") int pagina,
			@Parameter(description = "Número itens por página", required = true) @RequestParam(defaultValue = "10") int tamanho) {

		Page<ComunicadoSimplificadoDTO> comunicados = comunicadoService.obterTodos(pagina, tamanho)
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

	@DeleteMapping("/agendamentos/{id}")
	public ResponseEntity<?> removerSolicitacaoAgendamento(@PathVariable Long id) {
		comunicadoService.remover(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
