package com.acabes.fullStackToDo.controller;


import com.acabes.fullStackToDo.DTOs.*;
import com.acabes.fullStackToDo.exception.BadRequestExceptions;
import com.acabes.fullStackToDo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signup(@Valid @RequestBody UserDTO userDTO) {
        try {
            SignUpResponseDTO responseDTO = userService.registerUser(userDTO);
            ApiResponse<SignUpResponseDTO> apiResponse = new ApiResponse<>(true, "User registered successfully", responseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (BadRequestExceptions e) {
            ApiResponse<SignUpResponseDTO> apiResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<SignUpResponseDTO> apiResponse = new ApiResponse<>(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            LoginResponseDTO response = userService.loginUser(loginRequestDTO);
            ApiResponse<LoginResponseDTO> apiResponse = new ApiResponse<>(true, "Login successful", response);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (BadRequestExceptions e) {
            ApiResponse<LoginResponseDTO> apiResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<LoginResponseDTO> apiResponse = new ApiResponse<>(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
