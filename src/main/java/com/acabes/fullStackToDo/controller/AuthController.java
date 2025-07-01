package com.acabes.fullStackToDo.controller;


import com.acabes.fullStackToDo.DTOs.LoginRequestDTO;
import com.acabes.fullStackToDo.DTOs.LoginResponseDTO;
import com.acabes.fullStackToDo.DTOs.SignUpResponseDTO;
import com.acabes.fullStackToDo.DTOs.UserDTO;
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
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody UserDTO userDTO) {
        SignUpResponseDTO user = userService.registerUser(userDTO);
        try {
            SignUpResponseDTO responseDTO = userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "User registered successfully", "data", responseDTO, "success", true));
        } catch (BadRequestExceptions e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body( Map.of("message", e.getMessage(),"success", false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred","success", false));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = userService.loginUser(loginRequestDTO);
        Map<String, Object> responseMap = Map.of(
                "data", response,
                "success", true
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

}
