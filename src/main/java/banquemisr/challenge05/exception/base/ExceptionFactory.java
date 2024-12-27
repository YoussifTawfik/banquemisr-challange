package banquemisr.challenge05.exception.base;

import banquemisr.challenge05.exception.InvalidUsernameOrPasswordException;
import banquemisr.challenge05.exception.NotFoundException;
import banquemisr.challenge05.exception.UnauthorizedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionFactory {

    public static BaseException throwNotFoundException(){
        return new NotFoundException();
    }

    public static BaseException throwInvalidUsernameOrPasswordException(){
        return new InvalidUsernameOrPasswordException();
    }

    public static BaseException throwUnauthorizedException(){
        return new UnauthorizedException();
    }
}
