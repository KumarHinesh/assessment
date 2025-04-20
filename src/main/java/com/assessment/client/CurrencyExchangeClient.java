package com.assessment.client;

import com.assessment.dto.ExchangeApiResponse;
import com.assessment.exceptions.CurrencyExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
public class CurrencyExchangeClient {

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "exchangeRates", key = "#originalCurrency + '_' + #targetCurrency")
    public Optional<Double> getExchangeRate(String originalCurrency, String targetCurrency) {
        String url = String.format("%s%s/latest/%s", baseUrl, apiKey, originalCurrency);

        try {
            ExchangeApiResponse response = restTemplate.getForObject(url, ExchangeApiResponse.class);

            if (response != null && response.getConversionRates() != null) {
                return Optional.ofNullable(response.getConversionRates().get(targetCurrency));
            }
        } catch (RestClientException e) {
            throw new CurrencyExchangeException("Failed to fetch exchange rate from API", e);
        }

        return Optional.of(-1.0);
    }

}
