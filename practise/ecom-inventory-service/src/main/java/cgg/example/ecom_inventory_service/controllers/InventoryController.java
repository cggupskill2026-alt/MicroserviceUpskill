package cgg.example.ecom_inventory_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.example.ecom_inventory_service.model.Product;
import cgg.example.ecom_inventory_service.services.InventoryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
 private final InventoryService inventoryService;
	@GetMapping("/{productId}")
	public Product checkInventory(@PathVariable Long productId) throws InterruptedException {
//		return productId.equals("1")?"IN STOCK":"OUT OF STCOK";
//		Thread.sleep(15000);
		return inventoryService.checkStock(productId);
	}
	
	@PostMapping
	public String addProduct(@RequestBody Product product) {
		return inventoryService.addProduct(product);
	}
	
	@PutMapping
	public String updateProduct(@RequestBody Product product) {
		return inventoryService.updateProduct(product);
	}
}
