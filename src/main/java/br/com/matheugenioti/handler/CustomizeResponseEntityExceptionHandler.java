package br.com.matheugenioti.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.matheugenioti.exceptions.ExceptionResponse;
import br.com.matheugenioti.exceptions.RequiredObjectIsNullException;
import br.com.matheugenioti.exceptions.ResourceNotFoundException;
import br.com.matheugenioti.exceptions.InvalidJwtAuthenticationExceptions;
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionReponse = new ExceptionResponse(
				new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionReponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionReponse = new ExceptionResponse(
				new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionReponse, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionReponse = new ExceptionResponse(
				new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionReponse, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(InvalidJwtAuthenticationExceptions.class)
	public final ResponseEntity<ExceptionResponse> InvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionReponse = new ExceptionResponse(
				new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionReponse, HttpStatus.FORBIDDEN);
		
	}
}
