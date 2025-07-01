package com.acabes.fullStackToDo.controller;

import com.acabes.fullStackToDo.DTOs.ApiResponse;
import com.acabes.fullStackToDo.DTOs.TodoDTO;
import com.acabes.fullStackToDo.exception.BadRequestExceptions;
import com.acabes.fullStackToDo.model.Todo;
import com.acabes.fullStackToDo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ApiResponse<Todo>> createTodo(@RequestParam String userId, @Valid @RequestBody TodoDTO todoDTO) {
        try {
            Todo todo = todoService.createTodo(userId, todoDTO);
            ApiResponse<Todo> apiResponse = new ApiResponse<>(true, "Todo created successfully", todo);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (BadRequestExceptions e) {
            ApiResponse<Todo> apiResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<Todo> apiResponse = new ApiResponse<>(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Todo>>> getTodos(@RequestParam String userId) {
        try {
            List<Todo> todos = todoService.getTodosByUserId(userId);
            ApiResponse<List<Todo>> apiResponse = new ApiResponse<>(true, "Todos fetched successfully", todos);
            return ResponseEntity.ok(apiResponse);
        } catch (BadRequestExceptions e) {
            ApiResponse<List<Todo>> apiResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<List<Todo>> apiResponse = new ApiResponse<>(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestParam String userId, @Valid @RequestBody TodoDTO todoDTO) {
//        Todo updatedTodo = todoService.updateTodo(id, userId, todoDTO);
//        return ResponseEntity.ok(updatedTodo);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTodo(@PathVariable String id, @RequestParam String userId) {
//        todoService.deleteTodo(id, userId);
//        return ResponseEntity.noContent().build();
//    }
}