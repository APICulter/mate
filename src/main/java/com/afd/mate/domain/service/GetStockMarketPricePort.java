package com.afd.mate.domain.service;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface GetStockMarketPricePort {
    Mono<BigDecimal> get(String symbol);
}
