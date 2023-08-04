package com.example.products.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DecimalUtilTest {

    @Test
    public void testRoundWithTwoDecimalPlaces() {
        double value = 10.3456;
        int decimalPlaces = 2;

        double roundedValue = DecimalUtil.round(value, decimalPlaces);

        Assertions.assertEquals(10.35, roundedValue, 0.0001);
    }

    @Test
    public void testRoundWithZeroDecimalPlaces() {
        double value = 15.12345;
        int decimalPlaces = 0;

        double roundedValue = DecimalUtil.round(value, decimalPlaces);

        Assertions.assertEquals(15.0, roundedValue, 0.0001);
    }

    @Test
    public void testRoundWithLargeValueAndDecimalPlaces() {
        double value = 123456789.987654321;
        int decimalPlaces = 5;

        double roundedValue = DecimalUtil.round(value, decimalPlaces);

        Assertions.assertEquals(123456789.98765, roundedValue, 0.0001);
    }
}
