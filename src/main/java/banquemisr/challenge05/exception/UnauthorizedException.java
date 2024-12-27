package banquemisr.challenge05.exception;

import banquemisr.challenge05.exception.base.BaseException;
import banquemisr.challenge05.exception.base.ErrorCode;
import banquemisr.challenge05.exception.base.ErrorDetails;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class UnauthorizedException extends BaseException {

    private static final String ERROR_MESSAGE = "User is unauthorized!";

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED,
                ErrorDetails.builder().code(ErrorCode.ENTITY_ERROR_CODE+".03")
                        .message(ERROR_MESSAGE).timestamp(LocalDateTime.now()).build());
    }
}
