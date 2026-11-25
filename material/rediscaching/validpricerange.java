@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPriceRangeValidator.class)
@Documented
public @interface ValidPriceRange{
String message() default "Minimum price must not be greater than maximum price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    int minIndex();
    int maxIndex();
}