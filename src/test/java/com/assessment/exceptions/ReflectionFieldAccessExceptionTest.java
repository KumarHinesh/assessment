package com.assessment.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionFieldAccessExceptionTest {
    @Test
    void testReflectionFieldAccessException() {
        String message = "Field access error";
        Throwable cause = new RuntimeException("Cause of the error");

        ReflectionFieldAccessException exception = new ReflectionFieldAccessException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
