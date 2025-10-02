package com.jmp.pmanager.infrastructure.exception;


import java.util.List;
import java.util.Objects;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatusCode status, 
			WebRequest request) {
	
		List<String> detalhes = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(Objects::nonNull)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
		
		 return handleException(ex, "ErroValidacao", null, detalhes, HttpStatus.BAD_REQUEST, request);
	}
					
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		return handleException(ex, null, ex.getMessage(),null, HttpStatus.BAD_REQUEST, request);		
	}
	
	@ExceptionHandler(value=RequestException.class)
	public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request){
		return handleException(ex, ex.getCodigoErro(), ex.getMessage(),null, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request){
		return handleException(ex, null, ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);			
	}
	
	
	private ResponseEntity<Object> handleException(
			Exception ex, 
			String codigoErro,
			String mensagem,
			List<String> detalhes,
			HttpStatus status,
			WebRequest request
	){
		
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		
		return handleExceptionInternal(
				ex,
				RestError
					.builder()
					.codigoErro(codigoErro)
					.mensagemErro(mensagem)
					.detalhes(detalhes)
					.status(status.value())
					.path(servletWebRequest.getRequest().getRequestURI())
					.build(),
				new HttpHeaders(),
				status, 
				request
				);				
	}

}
