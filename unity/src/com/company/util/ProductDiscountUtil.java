package com.company.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductDiscountUtil {
    public static double calculateDiscount(int sumOfProductsPrices, int discount, int productPrice) {
        //format to 2 last digits (and round down rest) for e.g. 100.257 = 100[zł] 25[gr]
        BigDecimal fd = new BigDecimal((double) productPrice / sumOfProductsPrices * discount);
        BigDecimal cutted = fd.setScale(2, RoundingMode.DOWN);
        return cutted.doubleValue();
    }
}
