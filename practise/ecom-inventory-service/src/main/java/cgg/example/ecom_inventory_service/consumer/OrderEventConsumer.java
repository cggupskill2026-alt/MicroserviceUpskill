package cgg.example.ecom_inventory_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cgg.example.common_service.events.InventoryResponseEvent;
import cgg.example.common_service.events.OrderEvent;
import cgg.example.ecom_inventory_service.model.Product;
import cgg.example.ecom_inventory_service.producer.InventoryResponsePublisher;
import cgg.example.ecom_inventory_service.repository.InventoryRepository;
import cgg.example.ecom_inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventConsumer {
  private final InventoryService inventoryService;
  private final String ORDER_TOPIC="order-topic";
  private final InventoryResponsePublisher inventoryResponsePublisher;
 
  
  @KafkaListener(topics=ORDER_TOPIC,groupId="inventory-group")
  public void consumeOrderCreatedEvent(OrderEvent orderEvent) {
	  log.info("Received OrderEvent from Kafka : {}",orderEvent);
	  Product product=inventoryService.checkStock(orderEvent.getProductId());
	  inventoryService.updateProduct(product);
	  log.info("Product "+product);
	  InventoryResponseEvent responseEvent = new InventoryResponseEvent();
	  
	  responseEvent.setOrderId(orderEvent.getOrderId());
	  responseEvent.setProductId(orderEvent.getProductId());
	  responseEvent.setAvailable(true);
	  responseEvent.setMessage(product.getQuantity()>0?"Stock Available":"Insufficient Stock");
	  inventoryResponsePublisher.publishInventoryResponse(responseEvent);
	  
	  
  }
}
