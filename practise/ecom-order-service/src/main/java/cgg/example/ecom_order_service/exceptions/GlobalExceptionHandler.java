package cgg.example.ecom_order_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cgg.example.ecom_order_service.payload.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorResponse> handleProductException(ProductException e){
		ErrorResponse response = new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND,404);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
