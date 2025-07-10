package com.magalu.comunicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magalu.comunicados.domain.entity.Comunicado;

public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {

}
