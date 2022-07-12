package com.sgveteris.currency;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@EnableScheduling
@Configuration
@EnableConfigurationProperties
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Value("${spring.blockchain.url}")
    private String blockchainURL;

    public List<Currency> isFraudulentCustomer() {
        return currencyRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Currency getPrice(String coinType, String currencyType) {
        return currencyRepository.findByCurrencyTypeAndCoinType(currencyType, coinType)
                .orElseThrow(NullPointerException::new);
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 0)
    public void scheduleCurrency() throws JSONException {

        sendRequest(Symbol.BTC.getSymbol(), Symbol.USD.getSymbol());
        sendRequest(Symbol.BTC.getSymbol(), Symbol.EUR.getSymbol());
        sendRequest(Symbol.ETH.getSymbol(), Symbol.USD.getSymbol());
        sendRequest(Symbol.ETH.getSymbol(), Symbol.EUR.getSymbol());
    }

    private void sendRequest(String coinType, String currencyType) throws JSONException {
        JSONObject response =  new JSONObject(
                this.restTemplate.getForObject(
                        blockchainURL + coinType + "-" + currencyType, String.class));

        Currency currency = currencyRepository.findByCurrencyTypeAndCoinType(currencyType, coinType).
                orElse(new Currency());

        currency.setCurrencyType(currencyType);
        currency.setCoinType(coinType);
        currency.setLast_trade_price(response.getDouble("last_trade_price"));

        currencyRepository.save(currency);
    }

}
