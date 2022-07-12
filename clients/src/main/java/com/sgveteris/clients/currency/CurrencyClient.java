package com.sgveteris.clients.currency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "currency",
        url = "${clients.currency.url}"
)
public interface CurrencyClient {

    @GetMapping
    ResponseEntity<List<CurrencyResponse>> isFraudster() ;

    @GetMapping("/{coinType}/{currencyType}")
    ResponseEntity<CurrencyResponse> getPriceByCoinTypeAndCurrencyType(
            @PathVariable("coinType") String coinType, @PathVariable("currencyType") String currencyType) ;

}
