package cgg.example.ecom_order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cgg.example.ecom_order_service.client.config.InventoryFeignConfig;
import cgg.example.ecom_order_service.dto.Product;

@FeignClient(name="ecom-inventory-service",configuration=InventoryFeignConfig.class)
public interface InventoryClient {

	@GetMapping("/inventory/{productId}")
	ResponseEntity<Product> getInventory(@PathVariable long productId);
	
	@PostMapping("/inventory")
	void updateInventory(@RequestBody Product product);
}
