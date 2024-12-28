package banquemisr.challenge05.dto;

import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.enums.TodoPriority;
import banquemisr.challenge05.enums.TodoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoListingResponse {

    private String uuid;
    private String title;
    private String description;
    private TodoStatus status;
    private TodoPriority priority;
    private LocalDateTime dueDate;

    public TodoListingResponse(Todo todo) {
        this.uuid = todo.getUuid();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.priority = todo.getPriority();
        this.status = todo.getStatus();
        this.dueDate = todo.getDueDate();
    }

}
