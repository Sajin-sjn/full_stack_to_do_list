package com.acabes.fullStackToDo.DTOs;

import lombok.Data;

@Data
public class SignUpResponseDTO {
    private String id;
    private String user_id;

    public SignUpResponseDTO(String id, String user_id) {
        this.id = id;
        this.user_id = user_id;
    }

    // Getters and setters (or use Lombok @Data if preferred)
}

