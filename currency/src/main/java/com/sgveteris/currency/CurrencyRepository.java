package com.sgveteris.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository
        extends JpaRepository<Currency, Integer> {


    Optional<Currency> findByCurrencyTypeAndCoinType(String currencyType, String coinType);
}
