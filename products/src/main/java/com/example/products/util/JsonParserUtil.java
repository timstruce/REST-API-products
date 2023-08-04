package com.example.products.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParserUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static double parseUsdExchangeRate(String response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            String rate = jsonNode.get(0).get("srednji_tecaj").textValue();
            return Double.parseDouble(rate.replace(',', '.'));
        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
