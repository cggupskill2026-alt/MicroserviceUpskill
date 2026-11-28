package cgg.example.common_service.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryResponseEvent {
private Long orderId;
private Long productId;
private boolean available;
private String message;

}
