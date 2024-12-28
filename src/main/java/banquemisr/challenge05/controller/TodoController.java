package banquemisr.challenge05.controller;

import banquemisr.challenge05.dto.CreateTodoReqDto;
import banquemisr.challenge05.dto.CreateTodoResponse;
import banquemisr.challenge05.dto.TodoListingResponse;
import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.projection.TodoView;
import banquemisr.challenge05.security.JwtClaims;
import banquemisr.challenge05.service.TodoService;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final JwtClaims jwtClaims;

    @PostMapping
    public ResponseEntity<CreateTodoResponse> create(@Validated @RequestBody CreateTodoReqDto reqDto) {
        return new ResponseEntity<>(todoService.create(reqDto, jwtClaims.getUserUuid()), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TodoView> findByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(todoService.findByUuid(uuid, jwtClaims.getUserUuid()));
    }

    @GetMapping
    public ResponseEntity<Page<TodoListingResponse>> getAll(@SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)}) Pageable pageable, @Filter Specification<Todo> specification) {
        return ResponseEntity.ok(todoService.getAll(pageable, specification, jwtClaims.getUserUuid()));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        todoService.delete(uuid, jwtClaims.getUserUuid());
        return ResponseEntity.ok().build();
    }
}
