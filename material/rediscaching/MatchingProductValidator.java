public class MatchingProductValidator implements ConstraintValidator<MatchingProductId, Object[]> {
    
    public boolean isValid(Object[] arguments, ConstraintValidatorContext context) {
       if (arguments == null) {
           return true; // No validation needed if arguments are null or insufficient
       }
      var id = (Long) arguments[0];
      var request = (ProductRequest) arguments[1];
      return id==null || request==null || request.id()==null || id.equals(request.id());
    }
}