package cgg.example.common_service.events;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderEvent {
 private Long orderId;
 private String userId;
 private String status;
 private String message;
 private BigDecimal totalAmount;
 private String userEmail;
 private long productId;
 private int quantity;
}
