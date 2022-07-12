package com.sgveteris.order;

public record OrderRequest(
        String currencyType,
        String currencyAmount,
        String coinType) {
}
