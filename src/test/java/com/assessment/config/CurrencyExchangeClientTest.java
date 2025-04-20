package com.assessment.config;

import com.assessment.dto.ExchangeApiResponse;
import com.assessment.client.CurrencyExchangeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyExchangeClientTest {

    private RestTemplate restTemplate;
    private CurrencyExchangeClient currencyExchangeClient;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        currencyExchangeClient = new CurrencyExchangeClient(restTemplate);

        // Setting fields with reflection since @Value won't be injected in test
        try {
            var baseUrlField = CurrencyExchangeClient.class.getDeclaredField("baseUrl");
            baseUrlField.setAccessible(true);
            baseUrlField.set(currencyExchangeClient, "https://api.exchangerate-api.com/v4/");

            var apiKeyField = CurrencyExchangeClient.class.getDeclaredField("apiKey");
            apiKeyField.setAccessible(true);
            apiKeyField.set(currencyExchangeClient, "apikey/");
        } catch (Exception e) {
            throw new RuntimeException("Failed to set fields using reflection", e);
        }
    }

    @Test
    public void testGetExchangeRate_ReturnsCorrectRate() {
        // Arrange
        String originalCurrency = "USD";
        String targetCurrency = "PKR";
        double expectedRate = 280;

        ExchangeApiResponse mockResponse = new ExchangeApiResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put(targetCurrency, expectedRate);
        mockResponse.setConversionRates(rates);

        String expectedUrl = "https://api.exchangerate-api.com/v4/apikey//latest/USD";
        when(restTemplate.getForObject(expectedUrl, ExchangeApiResponse.class)).thenReturn(mockResponse);

        // Act
        double actualRate = currencyExchangeClient.getExchangeRate(originalCurrency, targetCurrency).get();

        // Assert
        assertEquals(expectedRate, actualRate);
    }

    @Test
    void testExchangeRateNotFound_throwsException() {
        String originalCurrency = "USD";
        String targetCurrency = "INVALID";

        // When
        double exchangeRate = currencyExchangeClient.getExchangeRate(originalCurrency, targetCurrency).get();

        // Then
        assertEquals(-1.0, exchangeRate, 0.0001);
    }
}
