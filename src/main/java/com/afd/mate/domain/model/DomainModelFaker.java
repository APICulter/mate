package com.afd.mate.domain.model;

import java.math.BigDecimal;
import com.github.javafaker.Faker;

public class DomainModelFaker {
    private static Faker faker = Faker.instance();

    public static StockPosition getFakeStockPosition(String symbol) {
        return new StockPosition(
                symbol,
                getQuantity(),
                getCode(),
                fakeAmount()
        );
    }

    public static String getCode() {
        return faker.currency().code();
    }

    public static BigDecimal fakeAmount() {
        return BigDecimal.valueOf(faker.number().randomDouble(4, 0, 10000000));
    }

    public static BigDecimal getQuantity() {
        return BigDecimal.valueOf(faker.number().randomDouble(2, 0, 10000));
    }

    public static String fakeStockSymbol() {
        return faker.stock().nsdqSymbol();
    }

//    public static StockPosition setFakeStockPosition(String symbol, BigDecimal quantity, Code code, BigDecimal amount) {
//        faker.code(code);
//
//    }
}
