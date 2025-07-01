package com.acabes.fullStackToDo.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse<T> {
    // Getters and Setters
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
