package com.assessment.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CurrencyExchangeExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String message = "Exchange rate not found";
        CurrencyExchangeException exception = new CurrencyExchangeException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Failed to connect to API";
        Throwable cause = new RuntimeException("Connection refused");

        CurrencyExchangeException exception = new CurrencyExchangeException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
