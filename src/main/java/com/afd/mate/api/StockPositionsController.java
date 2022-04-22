package com.afd.mate.api;

import com.afd.mate.domain.model.StockPosition;
import com.afd.mate.domain.service.GetStockMarketValueService;
import com.afd.mate.domain.service.GetStockPositionService;
import com.afd.mate.domain.service.PostStockPositionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class StockPositionsController {
    private final GetStockPositionService getStockPositionService;
    private final GetStockMarketValueService getStockMarketValueService;

    public StockPositionsController(GetStockPositionService getStockPositionService, GetStockMarketValueService getStockMarketValueService){
        this.getStockPositionService = getStockPositionService;
        this.getStockMarketValueService = getStockMarketValueService;
    }

    @GetMapping("/stock-position-market-value/{symbol}")
    Mono<GetStockPositionAndMarketValueApiResponseDto> getPositionAndMarketValue(
            @PathVariable String symbol
    ){
        return getStockPositionService.get(symbol)
                .zipWhen(stockPosition -> getStockMarketValueService.get(symbol, stockPosition.getQuantity()),
                        (stockPosition, marketValue) -> new GetStockPositionAndMarketValueApiResponseDto(
                                symbol,
                                stockPosition.getQuantity(),
                                stockPosition.getCurrencyCode(),
                                stockPosition.getCost(),
                                // placeholders
                                marketValue
                        ));
    }


}
