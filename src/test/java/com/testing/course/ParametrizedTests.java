package com.testing.course;

import com.testing.course.business.Cart;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParametrizedTests {
    private static Cart cart;

    @BeforeAll
    public static void setup() {
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);
        testCatalog.put("717029276-10", 150D);

        cart = new Cart(testCatalog);
        cart.setValidUser(true);
        cart.add("717029276-10", 1);
    }



    @ParameterizedTest
    //@CsvSource({"717029276-11", "717029276-12"})
    @MethodSource("getParameters")
    public void assertProductsNotSellBySupermarket(String product){
        assertThrows(RuntimeException.class, () -> cart.assertProductIsSellBySupermarket(product));
    }

    public static List<String> getParameters(){

        return Arrays.asList("717029276-11", "717029276-12");
    }
}
