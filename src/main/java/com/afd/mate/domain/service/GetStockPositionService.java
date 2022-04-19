package com.afd.mate.domain.service;

import com.afd.mate.domain.model.StockPosition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetStockPositionService {
    private final StockPositionRepository repository;

    public GetStockPositionService(StockPositionRepository repository) {
        this.repository = repository;
    }

    public Mono<StockPosition> get(String symbol
    ) {
        System.out.println("La connexion se fait bien");
        return repository.findOneBySymbol(symbol);
    }
}
