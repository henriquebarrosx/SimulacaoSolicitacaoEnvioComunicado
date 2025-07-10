package com.magalu.comunicados.dto.destinatario;

import io.swagger.v3.oas.annotations.media.Schema;

public record DestinatarioSimplificadoDTO(
		@Schema(description = "ID do destinatário.", example = "20") Long id,
		@Schema(description = "Nome do destinatário.", example = "John Doe") String nome,
		@Schema(description = "Email do destinatário.", example = "john.doe@mail.com") String email,
		@Schema(description = "Telefone do destinatário.", example = "82987293102") String telefone) {

}
