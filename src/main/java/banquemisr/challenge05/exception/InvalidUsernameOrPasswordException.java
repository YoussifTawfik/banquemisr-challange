package banquemisr.challenge05.exception;

import banquemisr.challenge05.exception.base.BaseException;
import banquemisr.challenge05.exception.base.ErrorCode;
import banquemisr.challenge05.exception.base.ErrorDetails;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidUsernameOrPasswordException extends BaseException {

    private static final String ERROR_MESSAGE = "Invalid username or password!";

    public InvalidUsernameOrPasswordException() {
        super(HttpStatus.BAD_REQUEST,
                ErrorDetails.builder().code(ErrorCode.ENTITY_ERROR_CODE+".02")
                        .message(ERROR_MESSAGE).timestamp(LocalDateTime.now()).build());
    }
}
