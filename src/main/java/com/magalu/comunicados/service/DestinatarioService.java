package com.magalu.comunicados.service;

import org.springframework.stereotype.Service;

import com.magalu.comunicados.domain.entity.Destinatario;
import com.magalu.comunicados.domain.objectValue.Telefone;
import com.magalu.comunicados.repository.DestinatarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DestinatarioService {

	private final DestinatarioRepository destinatarioRepository;

	public Destinatario cadastrar(String nome, String email, String contato) {
		Telefone telefone = new Telefone(contato);

		Destinatario destinatario = Destinatario.builder()
				.nome(nome)
				.email(email)
				.telefone(telefone.getValue())
				.build();

		return destinatarioRepository.save(destinatario);
	}

}
