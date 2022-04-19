package com.afd.mate.api;

import com.afd.mate.domain.model.DomainModelFaker;
import com.afd.mate.domain.model.StockPosition;
import com.afd.mate.domain.service.StockPositionRepository;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
//@TestPropertySource("classpath:application.properties")
public class GetStockPositionAndMarketValueApiE2ETest {

    @Autowired private ApplicationContext context;
    @MockBean
    private DomainModelFaker domainModelFaker;

    @Autowired
    private StockPositionRepository repository;


    @Test
    void getStockPositionAndMarketValue() {
        final WebTestClient client = WebTestClient.bindToApplicationContext(context).build();
        // arrange
        String symbol = domainModelFaker.fakeStockSymbol();
        StockPosition fakeStockPosition = domainModelFaker.getFakeStockPosition(symbol);
        // seed database
        repository.deleteAll()
                        .then(repository.insert(fakeStockPosition))
                                .block();

        // act
        client.get().uri("/stock-position-market-value/" + symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertAll(
                        () ->  assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () ->  assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(), Offset.offset(0.01)),
                        () ->  assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () ->  assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001)),
                        () ->  assertThat(dto.getMarketValue().doubleValue()).isGreaterThan(0.0))
                );
    }
}
