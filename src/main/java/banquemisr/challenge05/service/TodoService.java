package banquemisr.challenge05.service;

import banquemisr.challenge05.dto.*;
import banquemisr.challenge05.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {

    CreateTodoResponse create(CreateTodoReqDto reqDto, String userUuid);

    TodoDetailsResponseDto findByUuid(String todoUuid, String userUuid);

    Page<TodoListingResponse> getAll(Pageable pageable, Specification<Todo> specification, String userUuid);

    void delete(String todoUuid, String userUuid);

    void update(String todoUuid, String userUuid, UpdateTodoReqDto reqDto);

    List<Todo> fetchTodosWithinPeriod(LocalDateTime min, LocalDateTime max);

}
