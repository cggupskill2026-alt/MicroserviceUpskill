import java.math.BigDecimal;

@Service
    @RequiredArgsConstructor
    @Slf4j
    @Transactional(readOnly = true)
    public class CachedProductService{

        private final ProductRepository productRepository;

        @Cacheable(cacheNames = "product-details", key = "#id",condition = "#id != null")
        public ProdcutResponse getProductById(@Positive long id) {
            log.info("Fetching product details from database for id: {}", id);
          Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
            return ProdcutResponse.from(product);
        }

       @Cacheable(cacheNames = "product-pages")
       public ProdcutPageResponse listProducts(int page, int size, String category, @PositiveOrZero BigDecimal minPrice,@PositiveOrZero BigDecimal maxPrice) {
          return findProductPage(page, size, category, minPrice, maxPrice,null);
        }

        private ProdcutPageResponse findProductPage(int page, int size, String category, @PositiveOrZero BigDecimal minPrice,@PositiveOrZero BigDecimal maxPrice,String query) {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").ascending());
           
            Specification<Product> filters = buildFilters(category, minPrice, maxPrice,query);
            log.info("Fetching product page from database with filters: search={}, page={}, size={}", query!=null, page, s);
           

            return ProdcutPageResponse.from(productRepository.findAll(filters, pageable));
        }

        @Cacheable(cacheNames = "product-searches")
        public ProdcutPageResponse searchProducts(@NotBlank String query, int page, int size, String category, @PositiveOrZero BigDecimal minPrice,@PositiveOrZero BigDecimal maxPrice) {
            return findProductPage(page, size, category, minPrice, maxPrice,query);
        }

        @Transactional
        @caching(evict = { @CacheEvict(cacheNames = "product-details", key = "#request.id()"), @CacheEvict(cacheNames = {"product-pages", "product-searches", "product-lists"}, allEntries = true) })
        public ProductResponse saveProduct(ProductRequest request) {
            log.info("Saving new product to database: {}", request.id());
            if (productRepository.existsById(request.id())) {
                throw new RuntimeException("Product with id " + request.id() + " already exists");
            }
           Product product = new Product();
            product.setId(request.id());
            product.setCreatedAt(LocalDateTime.now());
            copyRequestToProduct(request, product);
            ProductResponse response = ProductResponse.from(productRepository.save(product));
            log.info("Product saved successfully: {}", product.getId());
            return response;
        }

        @Transactional
        @Cahing(evict = { @CacheEvict(cacheNames = "product-details", key = "#id"), @CacheEvict(cacheNames = {"product-pages", "product-searches", "product-lists"}, allEntries = true) })
        public ProductResponse updateProduct(@Positive Long id, ProductRequest request) {
            Product product = findProductOrThrow(id);
            copyRequestToProduct(request, product);
            productRePository.save(product);
            log.info("Updating product in database: {}", id);
            return ProductResponse.from(product);

            
        }

        @Transactional
        @Cahing(evict = { @CacheEvict(cacheNames = "product-details", key = "#id"), @CacheEvict(cacheNames = {"product-pages", "product-searches", "product-lists"}, allEntries = true) })
        public void deleteProduct(@Positive Long id) {
           productRepository.delete(findProductOrThrow(id));
            log.debug("Product deleted successfully: {}", id);
        }
        private Product findProductOrThrow(@Positive Long id) {
            return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        }

        private void copyRequestToProduct(ProductRequest request, Product product) {
            product.setName(request.name().trim());
            product.setCategory(request.category());
            product.setPrice(request.price());
            product.setStock(request.stock());
        }

        private Specification<Product> buildFilters(String category, @PositiveOrZero BigDecimal minPrice,@PositiveOrZero BigDecimal maxPrice,String query) {
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (category != null && !category.isBlank()) {
                    predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("category")), category.trim().toLowerCase(Locale.ROOT)));
                }
                if (minPrice != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
                }
                if (maxPrice != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
                }
                if (query != null && !query.isBlank()) {
                    String searchPattern = "%" + query.trim().toLowerCase(Locale.ROOT).replace("!", "!!").replace("%", "!%").replace("_", "!_") + "%";
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern,'!'),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), searchPattern,'!')
                    ));
                }
                return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
            };
        }

    }