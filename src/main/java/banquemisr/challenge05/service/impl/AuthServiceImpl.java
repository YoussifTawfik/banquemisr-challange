package banquemisr.challenge05.service.impl;

import banquemisr.challenge05.dto.AuthResponseDto;
import banquemisr.challenge05.dto.LoginReqDto;
import banquemisr.challenge05.entity.User;
import banquemisr.challenge05.enums.TokenClaims;
import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.repository.UserRepository;
import banquemisr.challenge05.service.AuthService;
import banquemisr.challenge05.service.RolePermissionService;
import banquemisr.challenge05.util.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RolePermissionService rolePermissionService;

    @Override
    @Transactional
    public AuthResponseDto login(LoginReqDto reqDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(reqDto.getEmail(), reqDto.getPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
            return generateJwtToken(reqDto.getEmail());
        } catch (Exception ex) {
            throw ExceptionFactory.throwInvalidUsernameOrPasswordException();
        }
    }

    private AuthResponseDto generateJwtToken(String userName) {
        AuthResponseDto result = new AuthResponseDto();
        Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET_CODE.getBytes());
        result.setToken(getAccessToken(userName, algorithm));
        result.setRefreshToken(generateRefreshToken(userName, algorithm));
        return result;
    }

    private String getAccessToken(String username, Algorithm algorithm) {
        User user = userRepository.findByEmail(username).orElseThrow(ExceptionFactory::throwNotFoundException);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
                .withClaim(TokenClaims.USER_ID.getValue(), user.getId())
                .withClaim(TokenClaims.USER_UUID.getValue(), user.getUuid())
                .withClaim(TokenClaims.USER_PERMISSIONS.getValue(), rolePermissionService.getPermCodesByRole(user.getRole().getId()))
                .sign(algorithm);
    }

    private String generateRefreshToken(String username, Algorithm algorithm) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .sign(algorithm);
    }
}
