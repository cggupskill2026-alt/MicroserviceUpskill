package cgg.example.ecom_backend_demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import cgg.example.ecom_backend_demo.entity.Product;

public record ProductResponse(
        long id,
        String name,
        String category,
        BigDecimal price,
        Integer stock,
        LocalDateTime createdAt) implements Serializable {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt());
    }

}
