package cgg.example.ecom_order_service.payload;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message,
		HttpStatus status,
		int code) {

}
