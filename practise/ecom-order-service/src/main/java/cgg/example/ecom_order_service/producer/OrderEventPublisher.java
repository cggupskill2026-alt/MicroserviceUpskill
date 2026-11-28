package cgg.example.ecom_order_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cgg.example.common_service.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {
 private final KafkaTemplate<String, Object> kafkaTemplate;
 private final String ORDER_TOPIC="order-topic";
 
 public void publishOrderCreatedEvent(OrderEvent orderEvent) {
	 this.kafkaTemplate.send(ORDER_TOPIC, orderEvent.getOrderId().toString(),orderEvent);
	 log.info("Order Event Published Successfully :"+orderEvent);
 }
}
