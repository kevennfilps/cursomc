package com.nelioalves.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Error", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataAccessException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Não Encontrado", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationErro err = new ValidationErro(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	
}
