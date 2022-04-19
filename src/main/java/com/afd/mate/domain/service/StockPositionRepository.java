package com.afd.mate.domain.service;

import com.afd.mate.domain.model.StockPosition;
import reactor.core.publisher.Mono;

public interface StockPositionRepository {
    Mono<StockPosition> findOneBySymbol(String symbol);

    Mono<Void> deleteAll();

    Mono<StockPosition> insert(StockPosition fakeStockPosition);
}
