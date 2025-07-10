package com.magalu.comunicados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.magalu.comunicados.dto.ExceptionDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionDTO> handleIllegalArgException(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(exception.getMessage()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionDTO> handleBadRequestException(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(exception.getMessage()));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDTO> handleNotFoundException(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(exception.getMessage()));
	}

}
