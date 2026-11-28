package cgg.example.ecom_inventory_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cgg.example.common_service.events.InventoryResponseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryResponsePublisher {

	private final KafkaTemplate<String,Object> kafkaTemplate;
	private final String INVENTORY_TOPIC="inventory-topic";
	
	public void publishInventoryResponse(InventoryResponseEvent event) {
		kafkaTemplate.send(INVENTORY_TOPIC,event.getOrderId().toString(),event);
		log.info("Inventory response published for order: "+event.getOrderId());
	}
	
}
