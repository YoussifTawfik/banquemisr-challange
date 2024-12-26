package banquemisr.challenge05.exception.base;

import org.springframework.http.ResponseEntity;
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
}
