package com.codestates.todo;

import com.codestates.dto.SingleResponseDto;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/")
@Validated
@Slf4j
public class TodoController {
    private final static String TODO_DEFAULT_URL = "/v1/todo";
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post todoDto) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(todoDto));
//        URI location = UriCreator.createUri(TODO_DEFAULT_URL, todo.getTodoId());
//
//        return ResponseEntity.created(location).build();

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponseDto(todo)),
                HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(
            @PathVariable("todo-id") @Positive long todoId,
            @Valid @RequestBody TodoDto.Patch requestBody) {
        requestBody.setTodoId(todoId);

        Todo todo =
                todoService.updateTodo(mapper.todoPatchDtoToTodo(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponseDto(todo)),
                HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity get(
            @PathVariable("todo-id") @Positive long todoId) {
        Todo todo = todoService.findTodo(todoId);
        todo.setTodoId(todoId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponseDto(todo))
                , HttpStatus.OK);
    }
//
    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();
        return new ResponseEntity<>(
                mapper.todosToTodoResponseDtos(todos),
                HttpStatus.OK);
    }
//
    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(
            @PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}