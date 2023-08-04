package com.example.products.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

public class HnbExchangeRateServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HnbExchangeRateService exchangeRateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchUsdExchangeRate() {
        String jsonResponse = "[{\"broj_tecajnice\":\"148\",\"datum_primjene\":\"2023-07-30\",\"drzava\":\"SAD\",\"drzava_iso\":\"USA\",\"sifra_valute\":\"840\",\"valuta\":\"USD\",\"kupovni_tecaj\":\"1,1027\",\"srednji_tecaj\":\"1,1010\",\"prodajni_tecaj\":\"1,0993\"}]";
        double expectedExchangeRate = 1.1010;

        when(restTemplate.getForObject(HnbExchangeRateService.HNB_API_URL_USD, String.class)).thenReturn(jsonResponse);

        double actualExchangeRate = exchangeRateService.fetchUsdExchangeRate();

        Assertions.assertEquals(expectedExchangeRate, actualExchangeRate, 0.5);
    }
}
