package com.afd.mate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPosition {
    private String symbol;
    private BigDecimal quantity;
    private String currencyCode;
    private BigDecimal cost;

}
