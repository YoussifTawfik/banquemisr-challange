package banquemisr.challenge05.controller;

import banquemisr.challenge05.dto.AuthResponseDto;
import banquemisr.challenge05.dto.LoginReqDto;
import banquemisr.challenge05.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(@Validated @RequestBody LoginReqDto reqDto) {
        return ResponseEntity.ok(authService.login(reqDto));
    }

}
