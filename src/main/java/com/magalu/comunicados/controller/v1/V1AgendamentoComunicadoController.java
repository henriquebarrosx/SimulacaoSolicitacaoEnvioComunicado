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

import com.magalu.comunicados.domain.entity.AgendamentoComunicado;
import com.magalu.comunicados.dto.ExceptionDTO;
import com.magalu.comunicados.dto.PaginacaoDTO;
import com.magalu.comunicados.dto.agendamento.AgendamentoIdDTO;
import com.magalu.comunicados.dto.agendamento.AgendamentoSimplificadoDTO;
import com.magalu.comunicados.dto.agendamento.PaginacaoComunicadoSimplificadoDTO;
import com.magalu.comunicados.dto.agendamento.SolicitacaoAgendamentoComunicadoDTO;
import com.magalu.comunicados.mapper.AgendamentoComunicadoMapper;
import com.magalu.comunicados.mapper.PaginacaoMapper;
import com.magalu.comunicados.service.AgendamentoComunicadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento dos agendamentos para envio dos comunicados")
@RequiredArgsConstructor
@RequestMapping("/v1/agendamentos")
@RestController
public class V1AgendamentoComunicadoController {

    private final AgendamentoComunicadoService agendamentoComunicadoService;
    private final AgendamentoComunicadoMapper agendamentoComunicadoMapper;
    private final PaginacaoMapper paginacaoMapper;

    @Operation(
            description = "Cadastra um agendamento para envio de comunicados"
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(
                    schema = @Schema(implementation = AgendamentoIdDTO.class),
                    mediaType = "application/json"
            )
    )
    @ApiResponse(
            responseCode = "400",
            content = @Content(
                    schema = @Schema(implementation = ExceptionDTO.class),
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Telefone Nulo",
                                    value = "{\"message\": \"Telefone não pode ser nulo\"}"
                            ),
                            @ExampleObject(
                                    name = "Formato de Telefone Inválido",
                                    value = "{\"message\": \"Formato de telefone inválido. Use (DD) XXXXX-XXXX ou DD XXXXX-XXXX.\"}"
                            ),
                    }
            )
    )
    @PostMapping
    public ResponseEntity<AgendamentoIdDTO> cadastrar(
            @RequestBody SolicitacaoAgendamentoComunicadoDTO solicitacao
    ) {
        AgendamentoComunicado agendamento = agendamentoComunicadoService.cadastrar(solicitacao);
        AgendamentoIdDTO idAgendamentoDTO = new AgendamentoIdDTO(agendamento.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(idAgendamentoDTO);
    }

    @Operation(
            description = "Retorna uma lista paginada com todos os agendamentos"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = PaginacaoComunicadoSimplificadoDTO.class),
                    mediaType = "application/json"
            )
    )
    @GetMapping
    public ResponseEntity<PaginacaoDTO<AgendamentoSimplificadoDTO>> obterTodos(
            @Parameter(description = "Número da pagina à carregar", required = true)
            @RequestParam(defaultValue = "0")
            int pagina,

            @Parameter(description = "Número de itens por página", required = true)
            @RequestParam(defaultValue = "10")
            int tamanho
    ) {
        Page<AgendamentoSimplificadoDTO> agendamentos = agendamentoComunicadoService.obterTodos(pagina, tamanho)
                .map(agendamentoComunicadoMapper::toAgendamentoSimplificado);

        var agendamentosPaginados = paginacaoMapper.toPaginacaoDTO(agendamentos);
        return ResponseEntity.ok().body(agendamentosPaginados);
    }

    @Operation(
            description = "Retorna um agendamento cadastrado"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = AgendamentoSimplificadoDTO.class),
                    mediaType = "application/json"
            )
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(
                    schema = @Schema(implementation = ExceptionDTO.class),
                    examples = @ExampleObject(value = "{\"message\": \"Agendamento não encontrado com o ID: 10 \"}"),
                    mediaType = "application/json"
            )
    )
    @GetMapping("{id}")
    public ResponseEntity<AgendamentoSimplificadoDTO> obterPorId(@PathVariable Long id) {
        AgendamentoComunicado agendamento = agendamentoComunicadoService.obterPorId(id);
        AgendamentoSimplificadoDTO agendamentoSimplificado = agendamentoComunicadoMapper.toAgendamentoSimplificado(agendamento);
        return ResponseEntity.ok().body(agendamentoSimplificado);
    }

    @Operation(
            description = "Realiza exclusão de agendamento"
    )
    @ApiResponse(
            responseCode = "204",
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(
                    schema = @Schema(implementation = ExceptionDTO.class),
                    examples = @ExampleObject(value = "{\"message\": \"Agendamento não encontrado com o ID: 10 \"}"),
                    mediaType = "application/json"
            )
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        agendamentoComunicadoService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
