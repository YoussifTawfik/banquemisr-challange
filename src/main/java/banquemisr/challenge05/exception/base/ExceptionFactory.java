package banquemisr.challenge05.exception.base;

import banquemisr.challenge05.exception.NotFoundException;

public class ExceptionFactory {

    public BaseException throwNotFoundException(){
        return new NotFoundException();
    }
}
