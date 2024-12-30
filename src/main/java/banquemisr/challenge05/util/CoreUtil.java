package banquemisr.challenge05.util;


import banquemisr.challenge05.exception.base.ExceptionFactory;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class CoreUtil {


    public static DecodedJWT getDecodedJWT() {
        Authentication authentication = getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            throw ExceptionFactory.throwInvalidTokenException();
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if(!(usernamePasswordAuthenticationToken.getDetails() instanceof DecodedJWT)) {
            throw ExceptionFactory.throwInvalidTokenException();
        }
        DecodedJWT decodedJWT = (DecodedJWT) usernamePasswordAuthenticationToken.getDetails();
        if(null == decodedJWT) {
            throw ExceptionFactory.throwInvalidTokenException();
        }
        return decodedJWT;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
