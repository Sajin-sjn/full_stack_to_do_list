package com.acabes.fullStackToDo.exception;

public class BadRequestExceptions extends RuntimeException {
    public BadRequestExceptions(String message) {
        super(message);
    }
}