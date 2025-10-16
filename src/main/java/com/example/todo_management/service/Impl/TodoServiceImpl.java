package com.example.todo_management.service.Impl;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.repository.TodoRepository;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    //private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert todoDto into Todo JPA Entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        //Todo JPA Entity
        Todo savedTodo = todoRepository.save(todo);

        //Convert saved todo JPA entity object into TodoDto object
        TodoDto savedDto = new TodoDto();

        savedDto.setId(savedTodo.getId());
        savedDto.setTitle(savedTodo.getTitle());
        savedDto.setDescription(savedTodo.getDescription());
        savedDto.setCompleted(savedTodo.isCompleted());

        return savedDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        return null;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return List.of();
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        return null;
    }

    @Override
    public void deleteTodo(Long id) {

    }

    @Override
    public TodoDto completeTodo(Long id) {
        return null;
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        return null;
    }
}
