package cgg.example.ecom_order_service.service.impl;

import java.net.URI;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cgg.example.ecom_order_service.client.InventoryClient;
import cgg.example.ecom_order_service.dto.Product;
import cgg.example.ecom_order_service.service.OrderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final InventoryClient inventoryClient;
//	private final DiscoveryClient discoveryClient;
	@Override
	public String placeOrder(long productId) {
		
//		List<ServiceInstance> instances = discoveryClient.getInstances("ecom-inventory-service");
//		ServiceInstance serviceInstance = instances.get(0);
//		URI uri = serviceInstance.getUri();
		ResponseEntity<Product> product=inventoryClient.getInventory(productId);
		System.out.println(product.getStatusCode());
				
		int quantity=0;
		if(product.getBody()!=null) {
			quantity=product.getBody().getQuantity();
			updateInventory(product.getBody());
		}
		return product.getBody()!=null&&quantity>0?"Order Placed Successfully":"Product Out of Stock";
	}

	private void updateInventory(Product product) {
		
	 if(product!=null) {
		product.setQuantity(product.getQuantity()-1);
	 }
		inventoryClient.updateInventory(product);
	}

}
