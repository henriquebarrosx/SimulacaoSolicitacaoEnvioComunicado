package com.magalu.comunicados.dto;

import java.util.List;

public record PaginacaoDTO<T>(List<T> data, TotalPaginacaoDTO total, int tamanho, int atual) {

}
