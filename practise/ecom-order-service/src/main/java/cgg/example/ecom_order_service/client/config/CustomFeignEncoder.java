package cgg.example.ecom_order_service.client.config;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import cgg.example.ecom_order_service.dto.Product;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomFeignEncoder implements Encoder {
 private  ObjectMapper objectMapper = new ObjectMapper();
	@Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        try {
            // Cast to Inventory
            if (object instanceof Product product) {

                // Transform into required structure
                Map<String, Object> data = new HashMap<>();
                data.put("productId", product.getProductId());
                data.put("quantity", product.getQuantity());

                String json = objectMapper.writeValueAsString(data);

                template.body(json);
                template.header("Content-Type", "application/json");
            }

        } catch (Exception e) {
            throw new RuntimeException("Encoding failed", e);
        }
    }


}
