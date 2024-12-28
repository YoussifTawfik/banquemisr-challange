package banquemisr.challenge05.security;

import banquemisr.challenge05.enums.TokenClaims;
import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.util.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Slf4j
public class CustomAuthorizationServer extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/auth/login")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                UsernamePasswordAuthenticationToken authenticationToken = extractAuthenticationToken(authorizationHeader);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }
    }

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
