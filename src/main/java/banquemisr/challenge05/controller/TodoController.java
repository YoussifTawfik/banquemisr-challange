package banquemisr.challenge05.controller;

import banquemisr.challenge05.dto.*;
import banquemisr.challenge05.entity.Todo;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final JwtClaims jwtClaims;

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public ResponseEntity<CreateTodoResponse> create(@Validated @RequestBody CreateTodoReqDto reqDto) {
        return new ResponseEntity<>(todoService.create(reqDto, jwtClaims.getUserUuid()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{uuid}")
    public ResponseEntity<TodoDetailsResponseDto> findByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(todoService.findByUuid(uuid, jwtClaims.getUserUuid()));
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public ResponseEntity<Page<TodoListingResponse>> getAll(@SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)}) Pageable pageable, @Filter Specification<Todo> specification) {
        return ResponseEntity.ok(todoService.getAll(pageable, specification, jwtClaims.getUserUuid()));
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        todoService.delete(uuid, jwtClaims.getUserUuid());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PatchMapping("/{uuid}")
    public ResponseEntity<Void> update(@PathVariable String uuid, @RequestBody UpdateTodoReqDto updateTodoReqDto) {
        todoService.update(uuid, jwtClaims.getUserUuid(), updateTodoReqDto);
        return ResponseEntity.ok().build();
    }
}
