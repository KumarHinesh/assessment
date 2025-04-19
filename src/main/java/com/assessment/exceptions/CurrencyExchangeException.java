package com.assessment.exceptions;

public class CurrencyExchangeException extends RuntimeException {
    public CurrencyExchangeException(String message) {
        super(message);
    }

    public CurrencyExchangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
