package com.afd.mate.domain.service;

import com.afd.mate.domain.model.StockPosition;
import com.github.javafaker.Stock;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class PostStockPositionService {
    private final StockPositionRepository repository;

    public PostStockPositionService(StockPositionRepository repository) {
        this.repository = repository;
    }

    public Mono<StockPosition> post(StockPosition stockPosition
    ) {
        return repository.insert(stockPosition);
    }
}
