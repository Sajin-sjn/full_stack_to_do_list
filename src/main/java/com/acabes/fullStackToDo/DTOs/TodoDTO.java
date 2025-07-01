package com.acabes.fullStackToDo.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class TodoDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private boolean completed;
}