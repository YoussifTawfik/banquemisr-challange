package banquemisr.challenge05.service;

import banquemisr.challenge05.dto.CreateTodoReqDto;
import banquemisr.challenge05.dto.CreateTodoResponse;
import banquemisr.challenge05.dto.TodoListingResponse;
import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.projection.TodoView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {

    CreateTodoResponse create(CreateTodoReqDto reqDto, String userUuid);

    TodoView findByUuid(String todoUuid, String userUuid);

    Page<TodoListingResponse> getAll(Pageable pageable, Specification<Todo> specification, String userUuid);

    void delete(String todoUuid, String userUuid);

    List<Todo> fetchTodosWithinPeriod(LocalDateTime min, LocalDateTime max);

}
