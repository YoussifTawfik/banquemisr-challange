package banquemisr.challenge05.exception.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {

    private String code;
    private String field;
    private String message;

    public ValidationErrorResponse(String message, String field){
        this.message=message;
        this.field = field;
    }

    public ValidationErrorResponse(String message, String field, String code){
        this.message=message;
        this.field = field;
        this.code = code;
    }

}
