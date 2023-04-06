package com.codestates.todo;


import com.codestates.dto.SingleResponseDto;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {

        return todoRepository.save(todo);

    }
//
    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.isCompleted())
                        .ifPresent((completed -> findTodo.setCompleted(completed)));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(TodoOrder -> findTodo.setTodoOrder(TodoOrder));

        return todoRepository.save(findTodo);
    }

    @Transactional(readOnly = true)
    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }


    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }
//
    public void deleteTodo(long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);

        todoRepository.delete(findTodo);
    }

    public void deleteTodos() {

        todoRepository.deleteAll();
    }
//

public Todo findVerifiedTodo(long todoId) {
    Optional<Todo> optionalTodo =
            todoRepository.findById(todoId);
    Todo findTodo =
            optionalTodo.orElseThrow(() ->
                    new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    return findTodo;
}
//
//    private void verifyExistsTitle(String email) {
//        Optional<Todo> todo = todoRepository.findByTitle(title);
//        if (todo.isPresent())
//            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
//    }
}