package com.sgveteris.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Symbol {

    BTC("BTC"),
    ETH("ETH"),
    USD("USD"),
    EUR("EUR");

    private String symbol;
}
