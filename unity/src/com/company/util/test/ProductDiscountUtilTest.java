package com.company.util.test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.company.util.ProductDiscountUtil.*;


public class ProductDiscountUtilTest {

    @Test
    public void shouldCorrectlyRoundAndMapValue(){
        //(300 / 800) * 9 = 3.375 so we expect 3.37
        assertEquals(calculateDiscount(800, 9, 300), 3.37);
    }
}
