package com.afd.mate.domain.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * GetStockPositionAndMarketValueApiResponseDTOAuto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-10T18:00:51.843525606Z[Etc/UTC]")
public class GetStockPositionAndMarketValueApiResponseDTOAuto   {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("quantity")
  private Double quantity;

  @JsonProperty("currencyCode")
  private String currencyCode;

  @JsonProperty("cost")
  private Double cost;

  @JsonProperty("marketValue")
  private Double marketValue;

  public GetStockPositionAndMarketValueApiResponseDTOAuto symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  /**
   * Get symbol
   * @return symbol
  */
  
  @Schema(name = "symbol", required = false)
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public GetStockPositionAndMarketValueApiResponseDTOAuto quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */
  
  @Schema(name = "quantity", required = false)
  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public GetStockPositionAndMarketValueApiResponseDTOAuto currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  /**
   * Get currencyCode
   * @return currencyCode
  */
  
  @Schema(name = "currencyCode", required = false)
  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public GetStockPositionAndMarketValueApiResponseDTOAuto cost(Double cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Get cost
   * @return cost
  */
  
  @Schema(name = "cost", required = false)
  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public GetStockPositionAndMarketValueApiResponseDTOAuto marketValue(Double marketValue) {
    this.marketValue = marketValue;
    return this;
  }

  /**
   * Get marketValue
   * @return marketValue
  */
  
  @Schema(name = "marketValue", required = false)
  public Double getMarketValue() {
    return marketValue;
  }

  public void setMarketValue(Double marketValue) {
    this.marketValue = marketValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetStockPositionAndMarketValueApiResponseDTOAuto getStockPositionAndMarketValueApiResponseDTOAuto = (GetStockPositionAndMarketValueApiResponseDTOAuto) o;
    return Objects.equals(this.symbol, getStockPositionAndMarketValueApiResponseDTOAuto.symbol) &&
        Objects.equals(this.quantity, getStockPositionAndMarketValueApiResponseDTOAuto.quantity) &&
        Objects.equals(this.currencyCode, getStockPositionAndMarketValueApiResponseDTOAuto.currencyCode) &&
        Objects.equals(this.cost, getStockPositionAndMarketValueApiResponseDTOAuto.cost) &&
        Objects.equals(this.marketValue, getStockPositionAndMarketValueApiResponseDTOAuto.marketValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, quantity, currencyCode, cost, marketValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetStockPositionAndMarketValueApiResponseDTOAuto {\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("    marketValue: ").append(toIndentedString(marketValue)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

