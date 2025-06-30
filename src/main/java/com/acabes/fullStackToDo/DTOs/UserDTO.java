package com.acabes.fullStackToDo.DTOs;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
}