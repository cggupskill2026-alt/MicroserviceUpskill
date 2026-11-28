package cgg.example.ecom_order_service.service.impl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cgg.example.common_service.events.OrderEvent;
import cgg.example.ecom_order_service.client.InventoryClient;
import cgg.example.ecom_order_service.dto.Product;
import cgg.example.ecom_order_service.model.ProductOrder;
import cgg.example.ecom_order_service.producer.OrderEventPublisher;
import cgg.example.ecom_order_service.repository.OrderRepository;
import cgg.example.ecom_order_service.service.OrderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderEventPublisher orderEventPublisher;
	private final OrderRepository orderRepository;
//	private final InventoryClient inventoryClient;
//	private final DiscoveryClient discoveryClient;
	@Override
	public String placeOrder(long productId) {
		
//		List<ServiceInstance> instances = discoveryClient.getInstances("ecom-inventory-service");
//		ServiceInstance serviceInstance = instances.get(0);
//		URI uri = serviceInstance.getUri();
//		ResponseEntity<Product> product=inventoryClient.getInventory(productId);
//		System.out.println(product.getStatusCode());
//				
//		int quantity=0;
//		if(product.getBody()!=null) {
//			quantity=product.getBody().getQuantity();
//			updateInventory(product.getBody());
//		}
//		return product.getBody()!=null&&quantity>0?"Order Placed Successfully":"Product Out of Stock";
		ProductOrder order=new ProductOrder();
		order.setOrderId(1L);
		order.setMessage("Order Placed");
		order.setProductId(productId);
		order.setQuantity(10);
		order.setStatus("PENDING");
        order.setTotalAmount(BigDecimal.valueOf(1000));	
        order.setUserId("123");
        order.setUserEmail("fatima.d@cgg.gov.in");
        
        orderRepository.save(order);
		
		
		OrderEvent orderEvent=new OrderEvent();
		orderEvent.setOrderId(1L);
		orderEvent.setProductId(productId);
		orderEvent.setTotalAmount(BigDecimal.valueOf(1000));
		orderEvent.setUserId("123");
		orderEvent.setUserEmail("fatima.d@cgg.gov.in");
		orderEvent.setStatus("PENDING");
		
		orderEventPublisher.publishOrderCreatedEvent(orderEvent);
		return "Order request submitted";
		
	}
	@Override
	public void updateOrderStatus(Long orderId, String message) {
		ProductOrder order = orderRepository.findById(orderId).orElseThrow();
		order.setStatus(message);
		orderRepository.save(order);
		
	}

//	private void updateInventory(Product product) {
//		
//	 if(product!=null) {
//		product.setQuantity(product.getQuantity()-1);
//	 }
//		inventoryClient.updateInventory(product);
//	}

}
