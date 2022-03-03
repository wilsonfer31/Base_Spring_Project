package fr.dawan.cfa2022.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.dawan.cfa2022.dto.ApiErrorDto;
import fr.dawan.cfa2022.exceptions.TokenException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {TokenException.class})
	protected ResponseEntity<?> handleTokenException(Exception ex, WebRequest request){
		
		ApiErrorDto error = new ApiErrorDto();
		error.setErrorCode(401);
		error.setMessage(ex.getMessage());
		error.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), 
				HttpStatus.UNAUTHORIZED, request);
		
	}
	
	
	
	@ExceptionHandler(value= {Exception.class})
	protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request){
		
		ApiErrorDto error = new ApiErrorDto();
		error.setErrorCode(500);
		error.setMessage(ex.getMessage());
		error.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		
	}
	

}