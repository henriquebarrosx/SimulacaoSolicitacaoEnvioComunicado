package com.magalu.comunicados.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.magalu.comunicados.dto.PaginacaoDTO;
import com.magalu.comunicados.dto.TotalPaginacaoDTO;

@Service
public class PaginacaoMapper {

	public <T> PaginacaoDTO<T> toPaginacaoDTO(Page<T> page) {
		TotalPaginacaoDTO totalPaginacao = new TotalPaginacaoDTO(page.getTotalPages(), page.getTotalElements());
		return new PaginacaoDTO<>(page.getContent(), totalPaginacao, page.getSize(), page.getNumber());
	}

}
