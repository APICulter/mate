package com.afd.mate.domain.service;

import com.afd.mate.domain.model.DomainModelFaker;
import com.afd.mate.domain.model.StockPosition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetStockPositionServiceTest {

    @MockBean
    private DomainModelFaker domainModelFaker;

    private final StockPositionRepository repository = mock(StockPositionRepository.class);
    private final GetStockPositionService subject = new GetStockPositionService(repository);

    @Test
    void get() {
        //arange
        String symbol = domainModelFaker.fakeStockSymbol();
        StockPosition fakeStockPosition = domainModelFaker.getFakeStockPosition(symbol);
        when(repository.findOneBySymbol(symbol)).thenReturn(Mono.just(fakeStockPosition));

        //act
        Mono<StockPosition> result = subject.get(symbol);

        // assert
        StepVerifier.create(result)
                .expectNext(fakeStockPosition)
                .verifyComplete();
    }
}