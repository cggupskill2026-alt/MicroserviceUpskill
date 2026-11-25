package cgg.example.ecom_backend_demo.service;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cgg.example.ecom_backend_demo.dto.ProductResponse;
import cgg.example.ecom_backend_demo.entity.Product;
import cgg.example.ecom_backend_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductNormalService {
    private final ProductRepository productRepository;
    private final StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductResponse getProductById(long id) {
        log.info("Getting product by id: {}", id);
        String key = "manual:product:" + id;
        @Nullable
        String cachedJson = redisTemplate.opsForValue().get(key);
        if (cachedJson != null) {
            log.info("Product found in cache: CACHE HIT");
            objectMapper.readValue(cachedJson, ProductResponse.class);
        }
        log.info("Product not found in cache : CACHE MISS");
        Product product = productRepository.findById(id).orElseThrow();
        var productResponse = ProductResponse.from(product);
        String json = objectMapper.writeValueAsString(productResponse);
        redisTemplate.opsForValue().set(key, json, Duration.ofMinutes(10));
        return productResponse;
    }
}
