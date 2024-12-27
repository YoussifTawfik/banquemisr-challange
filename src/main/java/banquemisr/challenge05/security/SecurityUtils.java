package banquemisr.challenge05.security;

import banquemisr.challenge05.enums.TokenClaims;
import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.util.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SecurityUtils {


    public UsernamePasswordAuthenticationToken extractAuthenticationToken(String authorizationHeader) {
        try {
            String token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET_CODE.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Long userId = decodedJWT.getClaim(TokenClaims.USER_ID.getValue()).asLong();
            List<String> userPrivileges = decodedJWT.getClaim(TokenClaims.USER_PERMISSIONS.getValue()).asList(String.class);
            List<SimpleGrantedAuthority> authorities = userPrivileges.stream().map(SimpleGrantedAuthority::new).toList();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authorities);
            authenticationToken.setDetails(decodedJWT);
            return authenticationToken;

        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            throw ExceptionFactory.throwUnauthorizedException();
        }
    }

}
