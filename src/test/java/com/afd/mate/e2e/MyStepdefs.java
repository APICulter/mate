package com.afd.mate.e2e;

import com.afd.mate.api.GetStockPositionAndMarketValueApiResponseDto;
import com.afd.mate.domain.model.DomainModelFaker;
import com.afd.mate.domain.model.GetStockPositionAndMarketValueApiResponseDTOAuto;
import com.afd.mate.domain.model.PostStockPositionAndMarketValueApiResponseDTOAuto;
import com.afd.mate.domain.model.StockPosition;
import com.afd.mate.domain.service.StockPositionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import net.minidev.json.JSONObject;
import org.assertj.core.data.Offset;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class MyStepdefs {

    @Autowired
    private ApplicationContext context;
    @MockBean
    private DomainModelFaker domainModelFaker;

    @Autowired
    private StockPositionRepository repository;


    WebTestClient client;

    WebTestClient.ResponseSpec clientResponse;
    String symbol;
    StockPosition fakeStockPosition;
    StockPosition fakeStockPositionCreate;

    @Given("I have in database a fakeStockPosition")
    public void iHaveInDatabaseAFakeStockPosition() {
        // arrange
        client = WebTestClient.bindToApplicationContext(context).build();


        symbol = domainModelFaker.fakeStockSymbol();
        fakeStockPosition = domainModelFaker.getFakeStockPosition(symbol);
        // seed database
        repository.deleteAll()
                .then(repository.insert(fakeStockPosition))
                .block();
    }

    @When("I want to retrieve by API my Stock Position and Market Value associated")
    public void iWantToRetrieveByAPIMyStockPositionAndMarketValueAssociated() {
        clientResponse = client.get().uri("/stock-position-market-value/" + symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Then("Value retrieved are corresponding to the value of the fake stock position")
    public void valueRetrievedAreCorrespondingToTheValueOfTheFakeStockPosition() {
        clientResponse.expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDto.class)
                .value(dto -> assertAll(
                        () ->  assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () ->  assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(), Offset.offset(0.01)),
                        () ->  assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () ->  assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001)),
                        () ->  assertThat(dto.getMarketValue().doubleValue()).isGreaterThan(0.0))
                );
    }

    @And("Market value in the object is filled")
    public void marketValueInTheObjectIsFilled() {
    }

    @When("I want to retrieve by generated API my Stock Position and Market Value associated")
    public void iWantToRetrieveByGeneratedAPIMyStockPositionAndMarketValueAssociated() {
        clientResponse = client.get().uri("/v2/auto-stock-position-market-value/" + symbol)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Then("Value retrieved in response body are corresponding to the value of the fake stock position")
    public void valueRetrievedInResponseBodyAreCorrespondingToTheValueOfTheFakeStockPosition() {
        clientResponse.expectStatus().isOk()
                .expectBody(GetStockPositionAndMarketValueApiResponseDTOAuto.class)
                .value(dto -> assertAll(
                        () ->  assertThat(dto.getSymbol()).isEqualTo(symbol),
                        () ->  assertThat(dto.getQuantity().doubleValue()).isCloseTo(fakeStockPosition.getQuantity().doubleValue(), Offset.offset(0.01)),
                        () ->  assertThat(dto.getCurrencyCode()).isEqualTo(fakeStockPosition.getCurrencyCode()),
                        () ->  assertThat(dto.getCost().doubleValue()).isCloseTo(fakeStockPosition.getCost().doubleValue(), Offset.offset(0.0001)),
                        () ->  assertThat(dto.getMarketValue().doubleValue()).isGreaterThan(0.0))
                );
    }

    @And("Market value in the object in response body is filled")
    public void marketValueInTheObjectInResponseBodyIsFilled() {
    }

    @Given("I have am empty Database")
    public void iHaveAmEmptyDatabase() {
        repository.deleteAll();
    }

    @When("I want to insert by API a Stock Position")
    public void iWantToInsertByAPIAStockPosition() {
        fakeStockPositionCreate = new StockPosition();
        fakeStockPositionCreate.setSymbol("IBM");
        fakeStockPositionCreate.setCost(BigDecimal.valueOf(1223));
        fakeStockPositionCreate.setQuantity(BigDecimal.valueOf(789465));
        fakeStockPositionCreate.setCurrencyCode("USD");
        ObjectMapper create = new ObjectMapper();
//        try {
//           clientResponse = client.post().uri("/v2/auto-stock-position-market-value/").contentType(MediaType.APPLICATION_JSON).bodyValue(create.writeValueAsString(fakeStockPositionCreate)).exchange();
//        } catch (Exception e){
//            System.out.println("raté");
//        }
        Gson jsonCreate = new Gson();
        String body = jsonCreate.toJson(fakeStockPositionCreate);
        clientResponse = client.post().uri("/v2/auto-stock-position-market-value/").contentType(MediaType.APPLICATION_JSON).bodyValue(body).exchange();

    }



}
