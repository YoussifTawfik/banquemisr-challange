package banquemisr.challenge05.service;

import banquemisr.challenge05.dto.AuthResponseDto;
import banquemisr.challenge05.dto.LoginReqDto;

public interface AuthService {

    AuthResponseDto login(LoginReqDto reqDto);

}
