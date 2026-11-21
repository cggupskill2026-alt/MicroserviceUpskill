package cgg.example.ecom_inventory_service.services;

import cgg.example.ecom_inventory_service.model.Product;

public interface InventoryService {
  Product checkStock(long prodcutId);

  String addProduct(Product product);

  String updateProduct(Product product);
}
