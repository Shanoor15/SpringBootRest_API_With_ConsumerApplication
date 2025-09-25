package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class globalExceptionalHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception e){
		return new ResponseEntity<String>("Internal server Error"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ActorNotFoundException.class)
	public ResponseEntity<String> handleANFException(ActorNotFoundException anfe){
		return new ResponseEntity<String>(anfe.getMessage(),HttpStatus.INSUFFICIENT_STORAGE);
	}
}
