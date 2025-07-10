package com.magalu.comunicados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DestinatarioDTO(
		@NotBlank(message = "Nome do destinatario não pode ser vazio") String nome,
		@NotBlank(message = "Email do destinatario não pode ser vazio") @Email(message = "Email inválido") String email,
		@NotBlank(message = "Telefone do destinatario não pode ser vazio") String telefone) {
}
