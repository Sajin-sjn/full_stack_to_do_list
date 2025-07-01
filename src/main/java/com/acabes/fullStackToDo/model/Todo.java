package com.acabes.fullStackToDo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private boolean completed;
}