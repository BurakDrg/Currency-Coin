package com.sgveteris.clients.currency;

public record CurrencyResponse(String currencyType, String coinType, Double last_trade_price) {
}
