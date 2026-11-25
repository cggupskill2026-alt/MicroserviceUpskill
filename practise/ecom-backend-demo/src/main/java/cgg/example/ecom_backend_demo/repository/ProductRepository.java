package cgg.example.ecom_backend_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.example.ecom_backend_demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
