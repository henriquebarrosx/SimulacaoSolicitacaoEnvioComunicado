package com.magalu.comunicados.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.magalu.comunicados.domain.enums.StatusAgendamentoComunicado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento_comunicado")
@Entity
public class AgendamentoComunicado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_hora_envio", nullable = false)
	private LocalDateTime dataHoraEnvio;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destinatario_id", referencedColumnName = "id")
	private Destinatario destinatario;

	@Column(name = "status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private StatusAgendamentoComunicado status;

	@Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
	private String mensagem;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}
