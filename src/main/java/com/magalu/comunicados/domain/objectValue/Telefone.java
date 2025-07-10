package com.magalu.comunicados.domain.objectValue;

import java.util.Objects;
import java.util.regex.Pattern;

import lombok.Getter;

@Getter
public class Telefone {

	private String value;

	public Telefone(String value) {
		final String TELEFONE_REGEX = "^\\(?([1-9]{2})\\)?\\s?(?:[2-9]|9[1-9])[0-9]{3}\\-?[0-9]{4}$";
		final Pattern TELEFONE_PATTERN = Pattern.compile(TELEFONE_REGEX);

		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("Telefone não pode ser nulo");
		}

		if (!TELEFONE_PATTERN.matcher(value).matches()) {
			throw new IllegalArgumentException("Formato de telefone inválido. Use (DD) XXXXX-XXXX ou DD XXXXX-XXXX.");
		}

		this.value = value;
	}

}
