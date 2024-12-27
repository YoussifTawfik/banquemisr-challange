package banquemisr.challenge05.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginReqDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
