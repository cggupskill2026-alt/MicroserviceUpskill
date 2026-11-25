public record ProductRequest(
    @NotNull(groups = CreateProduct.class, message = "Product ID must not be null")
    @Positive(message = "Product ID must be positive")Long id,
         @NotBlank(message = "Product name must not be blank")
         String name,
         @NotBlank(message = "Product category must not be blank")
         String category,
         @NotNull(message = "Product price must not be null")
         @PositiveOrZero(message = "Product price must be positive or zero")
         BigDecimal price,
         @NotNull(message = "Product stock must not be null")
            @PositiveOrZero(message = "Product stock must be positive or zero")
            Integer stock
 ) {}