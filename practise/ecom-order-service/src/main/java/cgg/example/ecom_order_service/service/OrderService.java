package cgg.example.ecom_order_service.service;

import cgg.example.ecom_order_service.dto.Product;

public interface OrderService {

String placeOrder(long productId);

void updateOrderStatus(Long orderId, String string);
}
