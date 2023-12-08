package com.example.currency.service;

import com.example.currency.webclient.CurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateService {

    private final CurrencyClient currencyClient;

    public Optional<String> getCurrencyRate() {
        String response = currencyClient.getRateForGivenCurrency("c", "usd")
                .orElse(null);
        log.info(response);
        return Optional.empty();
    }


}
