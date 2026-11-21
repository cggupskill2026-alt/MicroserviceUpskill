package cgg.example.ecom_order_service.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.example.ecom_order_service.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;
	@PostMapping("/{productId}")
	public String placeOrder(@PathVariable long productId) {
		return orderService.placeOrder(productId);
	}
}
