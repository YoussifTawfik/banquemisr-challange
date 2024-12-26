package banquemisr.challenge05.exception.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private String code;

    private String message;

    private LocalDateTime timestamp;
}
