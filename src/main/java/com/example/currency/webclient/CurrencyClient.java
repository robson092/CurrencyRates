package com.example.currency.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Component
public class CurrencyClient {

    private static final String CURRENCY_URL = "http://api.nbp.pl/api/exchangerates";
    private final RestTemplate restTemplate = new RestTemplate();

    private Optional<String> callGetMethod(String url, Object... objects) {
        return restTemplate.getForObject(CURRENCY_URL + url, Optional.class, objects);
    }

    public Optional<String> getRateForGivenCurrency(String table, String currencyCode) {
        return callGetMethod("/rates/{table}/{code}", table, currencyCode);
    }

    public Optional<String> getRateForGivenCurrencyInGivenDay(String table, String currencyCode, String date) {
        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{date}", table, currencyCode, date);
    }

    public Optional<String> getTodayRateForGivenCurrency(String table, String currencyCode) {
        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/", table, currencyCode, "today");
    }

    public Optional<String> getRateForGivenCurrencyInTimeRange(String table, String currencyCode,
                                                               String startDate, String endDate) {
        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{date}", table, currencyCode, startDate, endDate);
    }

    public Optional<String> getLastDaysRateForGivenCurrency(String table, String currencyCode, int topCount) {
        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{topCount}/", table, currencyCode,
                "last", topCount);
    }
}
