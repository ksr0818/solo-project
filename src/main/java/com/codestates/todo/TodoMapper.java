package com.codestates.todo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mapping(source = "order",target = "todoOrder")
    Todo todoPostDtoToTodo(TodoDto.Post todoPostDto);
    @Mapping(source = "order",target = "todoOrder")
    Todo todoPatchDtoToTodo(TodoDto.Patch todoPatchDto);
    @Mapping(source = "todoOrder",target = "order")
    TodoDto.Response todoToTodoResponseDto(Todo todo);
    @Mapping(source = "todoOrder",target = "order")
    List<TodoDto.Response> todosToTodoResponseDtos(List<Todo> todos);
}
