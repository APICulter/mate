package com.afd.mate.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostStockPositionAndMarketValueApiResponseDto {
    String symbol;
    Number quantity;
    String currencyCode;
    Number cost;
}
