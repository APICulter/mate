package com.afd.mate.domain.service;

import com.afd.mate.domain.model.DomainModelFaker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetStockMarketValueServiceTest {


    private GetStockMarketPricePort getStockMarketPricePort = mock(GetStockMarketPricePort.class);
    private GetStockMarketValueService subject = new GetStockMarketValueService(getStockMarketPricePort);

    @MockBean
    private DomainModelFaker domainModelFaker;

    @Test
    void get() {
        // arrange
        String symbol = domainModelFaker.fakeStockSymbol();
        BigDecimal quantity = domainModelFaker.getQuantity();
        BigDecimal fakePrice = domainModelFaker.fakeAmount();
        when(getStockMarketPricePort.get(symbol)).thenReturn(Mono.just(fakePrice));

        // act
        Mono<BigDecimal> result = subject.get(symbol, quantity);

        // assert
        StepVerifier.create(result)
                .expectNextMatches(amount -> amount.equals(quantity.multiply(fakePrice)))
                .verifyComplete();
    }
}