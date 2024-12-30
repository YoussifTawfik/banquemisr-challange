package banquemisr.challenge05.dto;

import banquemisr.challenge05.enums.TodoPriority;
import banquemisr.challenge05.enums.TodoStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDetailsResponseDto {
    private String uuid;

    private String title;

    private String description;

    private TodoStatus status;

    private TodoPriority priority;

    private LocalDateTime dueDate;

    private LocalDateTime createdAt;
}
