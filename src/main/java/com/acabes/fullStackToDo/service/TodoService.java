package com.acabes.fullStackToDo.service;

import com.acabes.fullStackToDo.DTOs.TodoDTO;
import com.acabes.fullStackToDo.exception.BadRequestExceptions;
import com.acabes.fullStackToDo.model.Todo;
import com.acabes.fullStackToDo.repository.TodoRepository;
import com.acabes.fullStackToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(String userId, TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setUserId(userId);
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByUserId(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new BadRequestExceptions("Invalid user ID");
        }
        return todoRepository.findByUserId(userId);
    }



//    public Todo updateTodo(String todoId, String userId, TodoDTO todoDTO) {
//        Todo todo = todoRepository.findById(todoId)
//                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));
//        if (!todo.getUserId().equals(userId)) {
//            throw new BadRequestException("Unauthorized to update this todo");
//        }
//        todo.setTitle(todoDTO.getTitle());
//        todo.setDescription(todoDTO.getDescription());
//        todo.setCompleted(todoDTO.isCompleted());
//        return todoRepository.save(todo);
//    }
//
//    public void deleteTodo(String todoId, String userId) {
//        Todo todo = todoRepository.findById(todoId)
//                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));
//        if (!todo.getUserId().equals(userId)) {
//            throw new BadRequestException("Unauthorized to delete this todo");
//        }
//        todoRepository.deleteById(todoId);
//    }
}