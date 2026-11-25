package cgg.example.ecom_backend_demo.controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cgg.example.ecom_backend_demo.dto.ProductResponse;
import cgg.example.ecom_backend_demo.service.CachedProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final CachedProductService cachedProductService;

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable long id) {
        return cachedProductService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> listProducts(@RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        return cachedProductService.listProducts(size, page);
    }

}
