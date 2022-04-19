package com.afd.mate.domain.service;

import com.afd.mate.domain.model.DomainModelFaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestPropertySource("classpath:application.properties")
class GetStockMarketPricePortIntegrationTest {

    @Autowired
    private GetStockMarketPricePort subject;

    @MockBean
    private DomainModelFaker domainModelFaker;

    @Test
    void get() {
        String symbol = domainModelFaker.fakeStockSymbol();

        Mono<BigDecimal> result = subject.get(symbol)
                .log();

        StepVerifier.create(result)
                .assertNext(item ->
                        assertThat(item).isGreaterThanOrEqualTo(BigDecimal.ZERO)
                )
                .verifyComplete();
    }
}