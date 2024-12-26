package banquemisr.challenge05.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception {

    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final ErrorDetails error;

    public BaseException(HttpStatus httpStatus, ErrorDetails error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }
}
