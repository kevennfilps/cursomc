package com.nelioalves.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErro extends StandardError {
	private static final long serialVersionUID = 1L;
	
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErro(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError (String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	

	
	
}
