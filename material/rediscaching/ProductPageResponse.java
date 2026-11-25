public record   ProductPageResponse(
         List<ProductResponse> products,
         int currentPage,
         long totalElements,
         int totalPages
 ) {
    public static ProductPageResponse from(Page<Product> productPage) {
         List<ProductResponse> productResponses = productPage.getContent().stream()
                 .map(ProductResponse::from)
                 .toList();
         return new ProductPageResponse(
                 productResponses,
                 productPage.getNumber(),
                 productPage.getTotalElements(),
                 productPage.getTotalPages()
         );
     }
 }