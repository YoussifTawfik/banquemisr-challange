package banquemisr.challenge05.service.impl;

import banquemisr.challenge05.dto.*;
import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.entity.User;
import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.repository.TodoRepository;
import banquemisr.challenge05.repository.UserRepository;
import banquemisr.challenge05.service.TodoService;
import banquemisr.challenge05.specification.TodoSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public CreateTodoResponse create(CreateTodoReqDto reqDto, String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(ExceptionFactory::throwNotFoundException);
        Todo todo = reqDto.mapToEntity();
        todo.setUser(user);
        todoRepository.save(todo);
        return new CreateTodoResponse(todo.getUuid());
    }

    @Override
    public TodoDetailsResponseDto findByUuid(String todoUuid, String userUuid) {
        Todo todo = todoRepository.findByUuidAndUserUuid(todoUuid, userUuid).orElseThrow(ExceptionFactory::throwNotFoundException);
        return objectMapper.convertValue(todo, TodoDetailsResponseDto.class);
    }

    @Override
    public Page<TodoListingResponse> getAll(Pageable pageable, Specification<Todo> specification, String userUuid) {
        specification = specification != null ? specification.and(TodoSpecification.filterByUserUuid(userUuid)) : TodoSpecification.filterByUserUuid(userUuid);
        return todoRepository.findAll(specification, pageable).map(TodoListingResponse::new);
    }

    @Override
    @Transactional
    public void delete(String todoUuid, String userUuid) {
        todoRepository.delete(todoUuid, userUuid);
    }

    @Override
    public void update(String todoUuid, String userUuid, UpdateTodoReqDto reqDto) {
        Todo todo = todoRepository.findByUuidAndUserUuid(todoUuid, userUuid).orElseThrow(ExceptionFactory::throwNotFoundException);
        boolean isUpdated = false;
        if (isNotBlank(reqDto.getTitle())) {
            todo.setTitle(reqDto.getTitle());
            isUpdated = true;
        }
        if (isNotBlank(reqDto.getDescription())) {
            todo.setDescription(reqDto.getDescription());
            isUpdated = true;
        }
        if (nonNull(reqDto.getStatus())) {
            todo.setStatus(reqDto.getStatus());
            isUpdated = true;
        }
        if (nonNull(reqDto.getPriority())) {
            todo.setPriority(reqDto.getPriority());
            isUpdated = true;
        }
        if (nonNull(reqDto.getDueDate())) {
            todo.setDueDate(reqDto.getDueDate());
            isUpdated = true;
        }
        if (isUpdated) {
            todoRepository.save(todo);
        }
    }

    @Override
    public List<Todo> fetchTodosWithinPeriod(LocalDateTime min, LocalDateTime max) {
        return todoRepository.fetchTodosInPeriod(min, max);
    }
}
