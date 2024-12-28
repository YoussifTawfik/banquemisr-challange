package banquemisr.challenge05.projection;

import banquemisr.challenge05.enums.TodoPriority;
import banquemisr.challenge05.enums.TodoStatus;

import java.time.LocalDateTime;

public interface TodoView {

    String getUuid();

    String getTitle();

    String getDescription();

    TodoStatus getStatus();

    TodoPriority getPriority();

    LocalDateTime getDueDate();

}
