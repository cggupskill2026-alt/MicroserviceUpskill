package cgg.example.ecom_inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.example.ecom_inventory_service.model.Product;

public interface InventoryRepository  extends JpaRepository<Product, Long>{

}
