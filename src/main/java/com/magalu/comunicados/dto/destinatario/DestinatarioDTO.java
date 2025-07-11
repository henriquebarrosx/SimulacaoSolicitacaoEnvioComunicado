package com.magalu.comunicados.dto.destinatario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DestinatarioDTO(
        @Schema(description = "Nome do destinatário", example = "John Doe")
        @NotBlank(message = "Nome do destinatario não pode ser vazio")
        String nome,

        @Schema(description = "E-mail do destinatário", example = "john.doe@mail.com")
        @NotBlank(message = "Email do destinatario não pode ser vazio")
        @Email(message = "Email inválido")
        String email,

        @Schema(description = "Telefone do destinatário", example = "82987398076")
        @NotBlank(message = "Telefone do destinatario não pode ser vazio")
        String telefone
) { }
