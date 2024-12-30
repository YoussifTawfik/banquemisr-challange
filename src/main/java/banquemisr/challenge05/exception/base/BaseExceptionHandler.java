package banquemisr.challenge05.exception.base;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDetails> customExceptionHandler(BaseException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorDetails(ex.getError().getCode(), ex.getError().getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> validationExceptionHandler(MethodArgumentNotValidException ex) {
        return handleMethodArgumentNotValidException(ex);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ValidationErrorResponse> authorizationExceptionHandler(AuthorizationDeniedException ex) {
        return handleUnauthorizedException();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorResponse> generalExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return handleGeneralException();
    }

    private ResponseEntity<ValidationErrorResponse> handleGeneralException() {
        ValidationErrorResponse response = new ValidationErrorResponse("Unexpected exception occurred, please contact support.",
                "G-600.01");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ValidationErrorResponse> handleUnauthorizedException() {
        ValidationErrorResponse response = new ValidationErrorResponse("User doesn't have the right permission!",null,
                "G-600.02");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        MethodArgumentNotValidException exp = ex;
        BindingResult result = exp.getBindingResult();
        FieldError fieldError = result.getFieldErrors().stream().findFirst().get();
        String message = fieldError.getDefaultMessage();
        if (null != message && message.contains(";")) {
            int index = message.lastIndexOf(';');
            String finalMessage = message.substring(0, index);
            String code = message.substring(index + 1);
            ValidationErrorResponse response = new ValidationErrorResponse(finalMessage, fieldError.getField(), code);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ValidationErrorResponse response = new ValidationErrorResponse(message, fieldError.getField());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
