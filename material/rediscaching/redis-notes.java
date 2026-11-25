@GetMapping
public ProdcutPageResponse listProducts(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                     @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(100) int size,
                                        @RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "minPrice", required = false) @PositiveOrZero BigDecimal minPrice,
                                        @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                      
)
{
    return cachedProductService.listProducts(page, size, category, minPrice, maxPrice);
}

@GetMapping("/search")
public ProdcutPageResponse searchProducts(
    @RequestParam(value = "q") @NotBlank String query,
    @RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                     @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(100) int size,
                                        @RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "minPrice", required = false) @PositiveOrZero BigDecimal minPrice,
                                        @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                      
)
{
    return cachedProductService.searchProducts(query,page, size, category, minPrice, maxPrice);
}

@PostMapping
public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Validated(CreateProduct.class) ProductRequest request) {
    ProductResponse saved = cachedProductService.saveProduct(request);
    return ResponseEntity.created(URI.create("/api/v1/products/" + saved.id())).body(saved);
}

@PutMapping("/{id}")
public ProductResponse updateProduct(@PathVariable("id") @Positive long id,@RequestBody @Valid ProductRequest request) {
    return cachedProductService.updateProduct(id, request);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable("id") @Positive long id) {
    cachedProductService.deleteProduct(id);
    return ResponseEntity.noContent().build();
}
