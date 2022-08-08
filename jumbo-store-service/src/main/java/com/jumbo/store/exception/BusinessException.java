package com.jumbo.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends Exception {

	private static final long serialVersionUID = -2253700169866865893L;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}

}
