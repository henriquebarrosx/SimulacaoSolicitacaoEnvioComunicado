package com.magalu.comunicados.dto.comunicado;

import java.util.List;

import com.magalu.comunicados.dto.PaginacaoDTO;
import com.magalu.comunicados.dto.TotalPaginacaoDTO;

public class PaginacaoComunicadoSimplificadoDTO extends PaginacaoDTO<ComunicadoSimplificadoDTO> {

	public PaginacaoComunicadoSimplificadoDTO(
			List<ComunicadoSimplificadoDTO> data,
			TotalPaginacaoDTO total,
			int tamanho,
			int atual) {
		super(data, total, tamanho, atual);
	}

}
