package com.example.products.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserUtilTest {

    @Test
    public void testParseUsdExchangeRateWithValidResponse() {
        String jsonResponse = "[{\"broj_tecajnice\":\"148\",\"datum_primjene\":\"2023-07-30\",\"drzava\":\"SAD\",\"drzava_iso\":\"USA\",\"sifra_valute\":\"840\",\"valuta\":\"USD\",\"kupovni_tecaj\":\"1,1027\",\"srednji_tecaj\":\"1,1010\",\"prodajni_tecaj\":\"1,0993\"}]";

        double exchangeRate = JsonParserUtil.parseUsdExchangeRate(jsonResponse);

        Assertions.assertEquals(1.1010, exchangeRate, 0.0001);
    }

    @Test
    public void testParseUsdExchangeRateWithInvalidResponse() {
        String invalidJsonResponse = "Invalid JSON response";

        double exchangeRate = JsonParserUtil.parseUsdExchangeRate(invalidJsonResponse);

        Assertions.assertEquals(0.0, exchangeRate, 0.0001);
    }

    @Test
    public void testParseUsdExchangeRateWithEmptyResponse() {
        String emptyJsonResponse = "[]";

        double exchangeRate = JsonParserUtil.parseUsdExchangeRate(emptyJsonResponse);

        Assertions.assertEquals(0.0, exchangeRate, 0.0001);
    }

    @Test
    public void testParseUsdExchangeRateWithMissingRateField() {
        String jsonResponse = "[{\"broj_tecajnice\":\"148\",\"datum_primjene\":\"2023-07-30\",\"drzava\":\"SAD\",\"drzava_iso\":\"USA\",\"sifra_valute\":\"840\",\"valuta\":\"USD\"}]";

        double exchangeRate = JsonParserUtil.parseUsdExchangeRate(jsonResponse);

        Assertions.assertEquals(0.0, exchangeRate, 0.0001);
    }

    @Test
    public void testParseUsdExchangeRateWithInvalidRateValue() {
        String jsonResponse = "[{\"broj_tecajnice\":\"148\",\"datum_primjene\":\"2023-07-30\",\"drzava\":\"SAD\",\"drzava_iso\":\"USA\",\"sifra_valute\":\"840\",\"valuta\":\"USD\",\"kupovni_tecaj\":\"1,1027\",\"srednji_tecaj\":\"invalid_rate\",\"prodajni_tecaj\":\"1,0993\"}]";

        double exchangeRate = JsonParserUtil.parseUsdExchangeRate(jsonResponse);

        Assertions.assertEquals(0.0, exchangeRate, 0.0001);
    }
}
