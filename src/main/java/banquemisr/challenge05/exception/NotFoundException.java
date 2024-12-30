package banquemisr.challenge05.exception;

import banquemisr.challenge05.exception.base.BaseException;
import banquemisr.challenge05.exception.base.ErrorDetails;
import banquemisr.challenge05.exception.base.ErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NotFoundException extends BaseException {

    private static final String ERROR_MESSAGE = "Entity Not Found!";

    public NotFoundException() {
        super(HttpStatus.BAD_REQUEST,
                ErrorDetails.builder().code(ErrorCode.ENTITY_ERROR_CODE+".01")
                        .message(ERROR_MESSAGE).timestamp(LocalDateTime.now()).build());
    }
}
