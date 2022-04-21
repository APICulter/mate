package com.afd.mate.api;

import com.afd.mate.domain.model.DomainModelFaker;
import com.afd.mate.domain.model.StockPosition;
import com.afd.mate.domain.service.GetStockMarketValueService;
import com.afd.mate.domain.service.GetStockPositionService;
import com.afd.mate.domain.service.PostStockPositionService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebFluxTest
public class GetStockPositionAndMarketValueApiTest {
    @Autowired private WebTestClient client;

    // Domain Service
    @MockBean
    private GetStockPositionService getStockPositionService;
    @MockBean
    private GetStockMarketValueService getStockMarketValueService;
    @MockBean
    private DomainModelFaker domainModelFaker;

    @MockBean
    private PostStockPositionService postStockPositionService;



    @Test
    void get() {
        // arrange
        String symbol = "aapl";
        StockPosition fakeStockPosition = domainModelFaker.getFakeStockPosition(symbol);
        when(getStockPositionService.get(symbol)).thenReturn(Mono.just(fakeStockPosition));
        BigDecimal fakeMarketPrice = domainModelFaker.fakeAmount();
        when(getStockMarketValueService.get(symbol, fakeStockPosition.getQuantity())).thenReturn(Mono.just(fakeMarketPrice));
        // act
        makeGetRequest(symbol)
                // assert
                .expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertAll(
                        () ->  assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () ->  assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(), Offset.offset(0.01)),
                        () ->  assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () ->  assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001)),
                        () ->  assertThat(dto.getMarketValue().doubleValue()).isCloseTo(fakeMarketPrice.doubleValue(), Offset.offset(0.0001)))
                );
    }

    private WebTestClient.ResponseSpec makeGetRequest(String symbol) {
        return client.get().uri("/stock-position-market-value/" + symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Test
    void emptyPosition() {
        String symbol = "aapl";
        String user = "peterpan";
        when(getStockPositionService.get(symbol)).thenReturn(Mono.empty());
        when(getStockMarketValueService.get(eq(symbol), any())).thenReturn(Mono.just(
                domainModelFaker.fakeAmount())
        );
        makeGetRequest("aapl")
                .expectStatus().isOk()
                .expectBody(Void.class);
    }


}
