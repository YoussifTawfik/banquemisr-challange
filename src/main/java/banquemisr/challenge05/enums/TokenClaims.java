package banquemisr.challenge05.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenClaims {

    USER_ID("userId"), USER_UUID("uuid"), USER_PERMISSIONS("userPermissions");

    private final String value;

}
