package cgg.example.ecom_backend_demo.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cgg.example.ecom_backend_demo.dto.ProductResponse;
import cgg.example.ecom_backend_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CachedProductService {
    private final ProductRepository productRepository;

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(long id) {
        log.info("Getting the product with id {}", id);
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        var productResponse = ProductResponse.from(product);
        return productResponse;
    }

    @Cacheable(value = "prodcuts-category-page", key = "#category + ':'+ #page+ ':'+#size")
    List<ProductResponse> listProductByCategory(String category, int page, int size) {
        return null;
    }

    @Cacheable(value = "products")
    // products::SimpleKey[]
    public List<ProductResponse> listProducts(int size, int page) {
        log.info("Getting products with size: {} and page: {} ", size, page);
        PageRequest pr = PageRequest.of(page, size);
        return productRepository.findAll(pr).getContent().stream().map(ProductResponse::from).toList();
    }
}
