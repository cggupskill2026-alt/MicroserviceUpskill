package cgg.example.ecom_order_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
 public class ProductOrder {

	@Id
	 private Long orderId;
	 private String userId;
	 private String status;
	 private String message;
	 private BigDecimal totalAmount;
	 private String userEmail;
	 private long productId;
	 private int quantity;
}
