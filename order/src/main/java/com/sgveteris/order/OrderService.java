package com.sgveteris.order;

import com.sgveteris.clients.currency.CurrencyClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class OrderService {

    private final SpendOrderRepository spendOrderRepository;

    private final CurrencyClient currencyClient;

    public SpendOrder registerCustomer(OrderRequest request) {

        SpendOrder order = SpendOrder.builder()
                .coinAmount(coinAmount(request))
                .coinType(request.coinType())
                .currencyAmount(request.currencyAmount())
                .currencyType(request.currencyType())
                .date(new Date(System.currentTimeMillis()))
                .build();

        spendOrderRepository.saveAndFlush(order);

        return order;
    }

    private String coinAmount(OrderRequest request){

        Double amount = Double.parseDouble(request.currencyAmount()) / currencyClient.getPriceByCoinTypeAndCurrencyType(request.coinType(), request.currencyType()).getBody().last_trade_price();
        return Double.toString(amount);
    }
}
