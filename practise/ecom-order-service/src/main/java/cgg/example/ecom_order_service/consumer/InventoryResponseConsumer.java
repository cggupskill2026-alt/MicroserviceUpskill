package cgg.example.ecom_order_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cgg.example.common_service.events.InventoryResponseEvent;
import cgg.example.ecom_order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryResponseConsumer {

	private final OrderService orderService;
	private final String INVENTORY_TOPIC="inventory-topic";
	
	@KafkaListener(topics="inventory-topic",groupId="order-group")
	public void consumeInventoryResponse(InventoryResponseEvent responseEvent) {
		log.info("Received Inventory Response for Order: "+responseEvent.getOrderId());
		if(responseEvent.isAvailable()) {
			orderService.updateOrderStatus(responseEvent.getOrderId(),"CONFIRMED");
			log.info("ORDER Confirmed");
		}
		else {
			orderService.updateOrderStatus(responseEvent.getOrderId(),"REJECTED");
			log.info("ORDER REJECTED");
		}
	}
}
