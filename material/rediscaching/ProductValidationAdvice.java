@RestControllerAdvice(assIgnableTypes = ProductController.class)
@sLf4j
public class ProductValidationAdvice  extends ResponseEntityExceptionHandler {
 
     @ExceptionHandler(ConstraintViolationException.class)
     public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex) {
         String errorMessage = ex.getConstraintViolations().stream()
                 .map(ConstraintViolation::getMessage)
                 .collect(Collectors.joining(", "));
         return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessage);
     }

     @ExceptionHandler(DataIntegrityViolationException.class)
     public ProblemDetail handleDatabseConflictException(DataIntegrityViolationException ex) {
         String errorMessage = "Database conflict: " + ex.getMostSpecificCause().getMessage();
         return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, errorMessage);
     }
 }