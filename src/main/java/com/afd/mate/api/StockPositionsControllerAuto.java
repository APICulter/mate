package com.afd.mate.api;

import com.afd.mate.domain.model.GetStockPositionAndMarketValueApiResponseDTOAuto;
import com.afd.mate.domain.model.StockPosition;
import com.afd.mate.domain.service.GetStockMarketValueService;
import com.afd.mate.domain.service.GetStockPositionService;
import com.afd.mate.domain.service.PostStockPositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
public class StockPositionsControllerAuto implements AutoStockPositionMarketValueApiDelegate{
    private final GetStockPositionService getStockPositionService;
    private final GetStockMarketValueService getStockMarketValueService;
    private final PostStockPositionService postStockPositionService;

    public StockPositionsControllerAuto(GetStockPositionService getStockPositionService, GetStockMarketValueService getStockMarketValueService, PostStockPositionService postStockPositionService){
        this.getStockPositionService = getStockPositionService;
        this.getStockMarketValueService = getStockMarketValueService;
        this.postStockPositionService = postStockPositionService;
    }

    @Override
    public Mono<ResponseEntity<GetStockPositionAndMarketValueApiResponseDTOAuto>> autoStockPositionMarketValueSymbolGet(String symbol,
                                                                                                             ServerWebExchange exchange) {
        return getStockPositionService.get(symbol)
                .zipWhen(stockPosition -> getStockMarketValueService.get(symbol, stockPosition.getQuantity()),
                        (stockPosition, marketValue) -> new ResponseEntity<>(new GetStockPositionAndMarketValueApiResponseDTOAuto()
                                .symbol(symbol)
                                .quantity(stockPosition.getQuantity().doubleValue())
                                .currencyCode(stockPosition.getCurrencyCode())
                                .cost(stockPosition.getCost().doubleValue())
                                .marketValue(marketValue.doubleValue()), HttpStatus.OK)
                        );
    };

    @PostMapping("/stock-position-market-value")
    Mono<StockPosition> createPositionAndMarketValue(
            @RequestBody StockPosition createSP
    ){
        //BigDecimal marketValue =  getStockMarketValueService.get(createSP.getSymbol(), createSP.getQuantity()).block();
        //createSP.setCost(marketValue);
        return postStockPositionService.post(createSP);
    }
}
