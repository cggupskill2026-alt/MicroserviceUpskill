@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ValidPriceRangeValidator implements ConstraintValidator<ValidPriceRange, Object[]> {
 
    private int minIndex;
    private int maxIndex;

    @Override
    public void initialize(ValidPriceRange constraintAnnotation) {
        this.minIndex = constraintAnnotation.minIndex();
        this.maxIndex = constraintAnnotation.maxIndex();
    }

    @Override
    public boolean isValid(Object[] arguments, ConstraintValidatorContext context) {
        if (arguments == null ){
            return true; // Let @NotNull handle null values
        }
        BigDecimal minPrice = (BigDecimal) arguments[minIndex];
        BigDecimal maxPrice = (BigDecimal) arguments[maxIndex];

        

        return minPrice==null || maxPrice==null|| minPrice.compareTo(maxPrice) <= 0;
     }
 }