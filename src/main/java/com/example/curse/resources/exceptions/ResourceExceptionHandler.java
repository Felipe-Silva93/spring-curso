package com.example.curse.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.curse.services.exceptions.DatabaseException;
import com.example.curse.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //vai interceptar as exceptions que acontecer e execultar os devidos tratamentos
public class ResourceExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)		/// dentro do parametro coloca o nome daexeção que vai estar interceptando //a anotation para que o metodo venha interceptar uma aceçao e ela venha cair nesse metodo a baixo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){//esse metodo vai ter que receber como argumento o a classe criada
		//esse metodo vai interceptar qual quer exeção que for lanchada do tipo ResourceNotFoundException e vai fazer o tratamento  que estiver dentro desse metodo
	
		String error = " recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;		//.value para passa para um valor inteiro
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
	
			//.status do metodo passando como parametro o status que foi definido
		return ResponseEntity.status(status).body(err);//passa como parametro o corpo da resposta que foi guardado na variavel err
				
	}
	
	@ExceptionHandler(DatabaseException.class)		/// dentro do parametro coloca o nome daexeção que vai estar interceptando //a anotation para que o metodo venha interceptar uma aceçao e ela venha cair nesse metodo a baixo
	public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){//esse metodo vai ter que receber como argumento o a classe criada
		//esse metodo vai interceptar qual quer exeção que for lanchada do tipo ResourceNotFoundException e vai fazer o tratamento  que estiver dentro desse metodo
	
		String error = " data base erro";
		HttpStatus status = HttpStatus.BAD_REQUEST;		//.value para passa para um valor inteiro
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
	
			//.status do metodo passando como parametro o status que foi definido
		return ResponseEntity.status(status).body(err);//passa como parametro o corpo da resposta que foi guardado na variavel err
				
	}
}
