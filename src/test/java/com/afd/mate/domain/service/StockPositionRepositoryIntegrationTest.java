package com.afd.mate.domain.service;

import com.afd.mate.domain.model.DomainModelFaker;
import com.afd.mate.domain.model.StockPosition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
//@TestPropertySource("classpath:application.properties")
class StockPositionRepositoryIntegrationTest {
    @Autowired
    private StockPositionRepository repository;

    @MockBean
    private DomainModelFaker domainModelFaker;

    @Test
    void findOneBySymbol() {


        // act
        String symbol = domainModelFaker.fakeStockSymbol();
        StockPosition fakeStockPosition = domainModelFaker.getFakeStockPosition(symbol);

        // seed database
        repository.deleteAll()
                .then(repository.insert(fakeStockPosition))
                .block(); // make sure it's completed first

        Mono<StockPosition> result = repository.findOneBySymbol(symbol);

        // assert
        StepVerifier.create(result)
                .assertNext(stockPosition ->
                        assertAll(
                                () -> assertThat(stockPosition.getSymbol()).isEqualTo(fakeStockPosition.getSymbol()),
                                () -> assertThat(stockPosition.getQuantity()).isEqualTo(fakeStockPosition.getQuantity()),
                                () -> assertThat(stockPosition.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                                () -> assertThat(stockPosition.getCost()).isEqualTo(fakeStockPosition.getCost())
                        )
                )
                .verifyComplete();
    }

    @Test
    void findBySymbolEmpty() {
        // act
        String symbol = domainModelFaker.fakeStockSymbol();

        // seed database
        repository.deleteAll()
                .block(); // make sure it's completed first

        Mono<StockPosition> result = repository.findOneBySymbol(symbol);

        StepVerifier.create(result)
                .verifyComplete();
    }
}