package com.assessment.exceptions;

public class ReflectionFieldAccessException extends RuntimeException {
    public ReflectionFieldAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
