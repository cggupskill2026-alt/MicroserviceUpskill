package cgg.example.ecom_backend_demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    private long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;

}
