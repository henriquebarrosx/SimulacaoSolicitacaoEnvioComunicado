package com.magalu.comunicados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magalu.comunicados.domain.entity.Destinatario;

public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

	Optional<Destinatario> findByEmail(String email);

	Optional<Destinatario> findByTelefone(String telefone);

}
