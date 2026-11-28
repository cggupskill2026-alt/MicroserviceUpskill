package cgg.example.ecom_inventory_service.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cgg.example.ecom_inventory_service.model.Product;
import cgg.example.ecom_inventory_service.repository.InventoryRepository;
import cgg.example.ecom_inventory_service.services.InventoryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	@Override
	public Product checkStock(long productId) {
		Optional<Product> inv=inventoryRepository.findById(productId);
		if(inv.isPresent()) {
			
		 Product p=inv.get();
		 if(p.getQuantity()>0) {
			 p.setQuantity(p.getQuantity()-1);
		 }
		 return p;
		}
		return null;
	}

	@Override
	public String addProduct(Product product) {
		inventoryRepository.save(product);
		return "Product Added";
	}

	@Override
	public String updateProduct(Product product) {
		inventoryRepository.save(product);
		return "Product Updated";
	}

}
