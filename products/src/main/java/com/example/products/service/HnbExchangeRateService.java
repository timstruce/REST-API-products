package com.example.products.service;

import com.example.products.util.JsonParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HnbExchangeRateService implements ExchangeRateService{

    protected static final String HNB_API_URL_USD = "https://api.hnb.hr/tecajn-eur/v3?valuta=USD";

    @Override
    public double fetchUsdExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(HNB_API_URL_USD, String.class);
        double usdExchangeRate = JsonParserUtil.parseUsdExchangeRate(response);

        return usdExchangeRate;
    }
}
