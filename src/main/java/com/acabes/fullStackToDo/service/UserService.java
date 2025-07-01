package com.acabes.fullStackToDo.service;

import com.acabes.fullStackToDo.DTOs.LoginRequestDTO;
import com.acabes.fullStackToDo.DTOs.LoginResponseDTO;
import com.acabes.fullStackToDo.DTOs.SignUpResponseDTO;
import com.acabes.fullStackToDo.DTOs.UserDTO;
import com.acabes.fullStackToDo.exception.BadRequestExceptions;
import com.acabes.fullStackToDo.model.User;
import com.acabes.fullStackToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SignUpResponseDTO registerUser(UserDTO userDTO){
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new BadRequestExceptions("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestExceptions("Email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Store plain password (not recommended for production)

        user.setUser_id(generateUserId());
        User savedUser = userRepository.save(user);
        return new SignUpResponseDTO(savedUser.getId(), savedUser.getUser_id());
    }


    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new BadRequestExceptions("Invalid username or password"));

        if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
            throw new BadRequestExceptions("Invalid username or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();
//        response.setUsername(user.getUsername());
//        response.setPassword(user.getPassword());
        response.setUserId(user.getId());
        return response;
    }

    private String generateUserId(){
        return "U-" + UUID.randomUUID().toString().substring(0,3).toUpperCase();
    }
}
