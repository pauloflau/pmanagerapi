package com.jmp.pmanager.infrastructure.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	 @Override
	 public ResponseEntity<Object> handleHttpMessageNotReadable(
	            HttpMessageNotReadableException ex,
	            HttpHeaders headers,
	            HttpStatusCode status,
	            WebRequest request) {

			return handleException(ex, null, ex.getMessage(), HttpStatus.BAD_REQUEST, request);		
	}
	
	@ExceptionHandler(value=RequestException.class)
	public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request){
		return handleException(ex, ex.getCodigoErro(), ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request){
		return handleException(ex, null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);			
	}
	
	
	private ResponseEntity<Object> handleException(
			Exception ex, 
			String codigoErro,
			String mensagem,
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
					.status(status.value())
					.path(servletWebRequest.getRequest().getRequestURI())
					.build(),
				new HttpHeaders(),
				status, 
				request
				);				
	}

}
