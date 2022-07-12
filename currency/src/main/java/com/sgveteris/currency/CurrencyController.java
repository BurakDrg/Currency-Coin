package com.sgveteris.currency;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
@AllArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> isFraudster() {
        return new ResponseEntity<>(currencyService.isFraudulentCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{coinType}/{currencyType}")
    public ResponseEntity<Currency> getPriceByCoinTypeAndCurrencyType(
            @PathVariable String coinType, @PathVariable String currencyType) {

        return new ResponseEntity<>(currencyService.getPrice(coinType,currencyType), HttpStatus.OK);
    }
}
