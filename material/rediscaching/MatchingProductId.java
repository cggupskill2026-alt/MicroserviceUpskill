@Target(ELEMENT_TYPE.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchingProductIdValidator.class)
@Documented
public @interface MatchingProductId{
String message() default "Product ID in path variable does not match the product ID in the request body";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}