package com.assessment.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeApiResponseTest {

    @Test
    public void testSettersAndGetters() {
        ExchangeApiResponse response = new ExchangeApiResponse();

        response.setResult("success");
        response.setDocumentation("https://www.exchangerate-api.com/docs");
        response.setTermsOfUse("https://www.exchangerate-api.com/terms");
        response.setTimeLastUpdateUnix(1612137600L);
        response.setTimeLastUpdateUtc("2021-02-01T00:00:00Z");
        response.setTimeNextUpdateUnix(1612224000L);
        response.setTimeNextUpdateUtc("2021-02-02T00:00:00Z");
        response.setBaseCode("USD");
        response.setConversionRates(Map.of("INR", 85.488, "PKR", 280.0, "AED", 3.6725));

        assertEquals("success", response.getResult());
        assertEquals("https://www.exchangerate-api.com/docs", response.getDocumentation());
        assertEquals("https://www.exchangerate-api.com/terms", response.getTermsOfUse());
        assertEquals(1612137600L, response.getTimeLastUpdateUnix());
        assertEquals("2021-02-01T00:00:00Z", response.getTimeLastUpdateUtc());
        assertEquals(1612224000L, response.getTimeNextUpdateUnix());
        assertEquals("2021-02-02T00:00:00Z", response.getTimeNextUpdateUtc());
        assertEquals("USD", response.getBaseCode());
        assertEquals(280.0, response.getConversionRates().get("PKR"));
    }

    @Test
    public void testJsonDeserialization() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String json = """
                        {
                             "result": "success",
                             "documentation": "https://www.exchangerate-api.com/docs",
                             "terms_of_use": "https://www.exchangerate-api.com/terms",
                             "time_last_update_unix": 1612137600,
                             "time_last_update_utc": "2021-02-01T00:00:00Z",
                             "time_next_update_unix": 1612224000,
                             "time_next_update_utc": "2021-02-02T00:00:00Z",
                             "base_code": "USD",
                             "conversion_rates": {
                                 "USD": 1,
                                 "AED": 3.6725,
                                 "ETB": 132.3248,
                                 "EUR": 0.8783,
                                 "GBP": 0.7534,
                                 "INR": 85.4880,
                                 "ISK": 127.6876,
                                 "NOK": 10.4856,
                                 "PKR": 280.0,
                                 "TWD": 32.4645,
                                 "WST": 2.7770,
                                 "XAF": 576.1455,
                                 "XCD": 2.7000,
                                 "XCG": 1.7900,
                                 "ZMW": 28.4856,
                                 "ZWL": 26.7994
                             }
                         }
                """;
        ExchangeApiResponse response = mapper.readValue(json, ExchangeApiResponse.class);

        assertEquals("success", response.getResult());
        assertEquals("https://www.exchangerate-api.com/docs", response.getDocumentation());
        assertEquals("https://www.exchangerate-api.com/terms", response.getTermsOfUse());
        assertEquals(1612137600L, response.getTimeLastUpdateUnix());
        assertEquals("2021-02-01T00:00:00Z", response.getTimeLastUpdateUtc());
        assertEquals(1612224000L, response.getTimeNextUpdateUnix());
        assertEquals("2021-02-02T00:00:00Z", response.getTimeNextUpdateUtc());
        assertEquals("USD", response.getBaseCode());
        assertEquals(85.488, response.getConversionRates().get("INR"));
        assertEquals(280.0, response.getConversionRates().get("PKR"));
        assertEquals(3.6725, response.getConversionRates().get("AED"));
    }
}
