package banquemisr.challenge05.dto;

import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.enums.TodoPriority;
import banquemisr.challenge05.enums.TodoStatus;
import banquemisr.challenge05.exception.base.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTodoReqDto {

    @NotBlank(message = "Title can't be null or empty;"+ ErrorCode.VALIDATION_ERROR_CODE+".01")
    private String title;

    @NotBlank(message = "Description can't be null or empty;"+ ErrorCode.VALIDATION_ERROR_CODE+".02")
    private String description;

    @NotNull(message = "Status can't be null or empty;"+ ErrorCode.VALIDATION_ERROR_CODE+".03")
    private TodoStatus status;

    @NotNull(message = "Priority can't be null or empty;"+ ErrorCode.VALIDATION_ERROR_CODE+".04")
    private TodoPriority priority;

    @NotNull(message = "Due Date can't be null or empty;"+ ErrorCode.VALIDATION_ERROR_CODE+".05")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;


    public Todo mapToEntity() {
        return Todo.builder()
                .uuid(UUID.randomUUID().toString())
                .title(this.title)
                .description(this.description)
                .status(this.status)
                .priority(this.priority)
                .dueDate(this.dueDate)
                .build();
    }

}
