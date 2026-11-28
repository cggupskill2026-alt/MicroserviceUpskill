package cgg.example.ecom_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.example.ecom_order_service.model.ProductOrder;
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

}
