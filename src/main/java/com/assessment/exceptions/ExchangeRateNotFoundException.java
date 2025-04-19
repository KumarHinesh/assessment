package com.assessment.exceptions;

public class ExchangeRateNotFoundException extends RuntimeException {
    public ExchangeRateNotFoundException(String from, String to) {
        super("Exchange rate not found from " + from + " to " + to);
    }
}
