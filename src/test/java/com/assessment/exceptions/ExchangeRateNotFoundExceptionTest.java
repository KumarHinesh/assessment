package com.assessment.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExchangeRateNotFoundExceptionTest {

    @Test
    void testMessageFormatting() {
        String fromCurrency = "USD";
        String toCurrency = "PKR";

        ExchangeRateNotFoundException exception = new ExchangeRateNotFoundException(fromCurrency, toCurrency);

        String expectedMessage = "Exchange rate not found from USD to PKR";
        assertEquals(expectedMessage, exception.getMessage());
        assertNull(exception.getCause());
    }
}