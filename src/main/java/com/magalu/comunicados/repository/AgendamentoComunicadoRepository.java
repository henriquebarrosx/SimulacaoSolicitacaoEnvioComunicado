package com.magalu.comunicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magalu.comunicados.domain.entity.AgendamentoComunicado;

public interface AgendamentoComunicadoRepository extends JpaRepository<AgendamentoComunicado, Long> {

}
