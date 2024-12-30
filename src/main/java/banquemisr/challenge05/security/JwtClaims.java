package banquemisr.challenge05.security;

import banquemisr.challenge05.enums.TokenClaims;
import banquemisr.challenge05.util.CoreUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Getter
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JwtClaims {

    private Long userId;
    private String userUuid;


    @PostConstruct
    public void init() {
        DecodedJWT decodedJwt = CoreUtil.getDecodedJWT();
        userId = decodedJwt.getClaim(TokenClaims.USER_ID.getValue()).asLong();
        userUuid = decodedJwt.getClaim(TokenClaims.USER_UUID.getValue()).asString();
    }
}
