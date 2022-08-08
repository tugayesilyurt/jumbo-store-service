package com.jumbo.store.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ExceptionResponse {

	private String errorMessage;
	private Date timestamp;
	private String ip;
	private String path;
	private String rejectedValue;
	private String fieldName;
	
}
