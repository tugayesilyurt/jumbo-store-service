package com.jumbo.store.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerGeneric {
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody List<ExceptionResponse> handleRequestNotValid(MethodArgumentNotValidException exception,final HttpServletRequest request) {
		
		log.error("MethodArgumentNotValidException",exception);
		
		List<ExceptionResponse> listResponse = new ArrayList<ExceptionResponse>();

		exception.getFieldErrors().stream().forEach(item -> {
			ExceptionResponse error = new ExceptionResponse();
			error.setErrorMessage(item.getDefaultMessage());
			error.setTimestamp(new Date());
			error.setIp(request.getRemoteAddr());
			error.setPath(request.getServletPath());
			error.setRejectedValue(Objects.nonNull(item.getRejectedValue()) ? item.getRejectedValue().toString() : null);
			error.setFieldName(item.getField());
			listResponse.add(error);
		});
		
		return listResponse;
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleBusinessException(final BusinessException exception,
			final HttpServletRequest request) {
		
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(new Date());
		error.setIp(request.getRemoteAddr());
		error.setPath(request.getServletPath());
		
		log.error("BusinessException " +exception.getMessage());
		exception.printStackTrace();
		
		return error;
	}
	

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {
		
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(new Date());
		error.setIp(request.getRemoteAddr());
		error.setPath(request.getServletPath());
		
		log.error("Exception " + exception.getMessage());
		exception.printStackTrace();
		
		return error;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleRuntimeException(final Exception exception,
			final HttpServletRequest request) {
		
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(new Date());
		error.setIp(request.getRemoteAddr());
		error.setPath(request.getServletPath());
		
		log.error("Exception " + exception.getMessage());
		exception.printStackTrace();
		
		return error;
	}


}
