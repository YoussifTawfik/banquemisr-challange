package banquemisr.challenge05.service.impl;

import banquemisr.challenge05.dto.CreateTodoReqDto;
import banquemisr.challenge05.dto.CreateTodoResponse;
import banquemisr.challenge05.dto.TodoListingResponse;
import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.entity.User;
import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.projection.TodoView;
import banquemisr.challenge05.repository.TodoRepository;
import banquemisr.challenge05.repository.UserRepository;
import banquemisr.challenge05.service.TodoService;
import banquemisr.challenge05.specification.TodoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public CreateTodoResponse create(CreateTodoReqDto reqDto, String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(ExceptionFactory::throwNotFoundException);
        Todo todo = reqDto.mapToEntity();
        todo.setUser(user);
        todoRepository.save(todo);
        return new CreateTodoResponse(todo.getUuid());
    }

    @Override
    public TodoView findByUuid(String todoUuid, String userUuid) {
        return todoRepository.findByUuidAndUserUuid(todoUuid, userUuid);
    }

    @Override
    public Page<TodoListingResponse> getAll(Pageable pageable, Specification<Todo> specification, String userUuid) {
        specification = specification!=null ? specification.and(TodoSpecification.filterByUserUuid(userUuid)): TodoSpecification.filterByUserUuid(userUuid);
        return todoRepository.findAll(specification, pageable).map(TodoListingResponse::new);
    }

    @Override
    @Transactional
    public void delete(String todoUuid, String userUuid) {
        todoRepository.delete(todoUuid, userUuid);
    }

    @Override
    public List<Todo> fetchTodosWithinPeriod(LocalDateTime min, LocalDateTime max) {
        return todoRepository.fetchTodosInPeriod(min, max);
    }
}
