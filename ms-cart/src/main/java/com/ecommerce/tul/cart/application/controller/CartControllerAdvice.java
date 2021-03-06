package com.ecommerce.tul.cart.application.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.tul.ecommercecommons.exception.BussinessException;
import com.ecommerce.tul.ecommercecommons.exception.TechnicalException;

@ControllerAdvice
public class CartControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<Object> bussinesError(BussinessException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<Object> technicalError(TechnicalException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
