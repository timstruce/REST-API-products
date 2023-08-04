package com.example.products.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalUtil {

    public static double round(double value, int decimalPlaces) {
        BigDecimal result = BigDecimal.valueOf(value).setScale(decimalPlaces, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}
