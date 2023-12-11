package com.example.currency.webclient;

import com.example.currency.dto.NbpCurrencyDto;
import com.example.currency.dto.NbpCurrencyRatesDto;
import com.example.currency.model.CurrencyRateDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyClient {

    private static final String CURRENCY_URL = "http://api.nbp.pl/api/exchangerates";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(CURRENCY_URL + url, responseType, objects);
    }

    public CurrencyRateDto getRateForGivenCurrency(String table, String currencyCode) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("/rates/{table}/{code}", NbpCurrencyDto.class,
                table, currencyCode);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }

//    public Optional<String> getRateForGivenCurrencyInGivenDay(String table, String currencyCode, String date) {
//        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{date}", String.class, table, currencyCode, date);
//    }
//
//    public Optional<String> getTodayRateForGivenCurrency(String table, String currencyCode) {
//        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/", String.class, table, currencyCode, "today");
//    }
//
//    public Optional<String> getRateForGivenCurrencyInTimeRange(String table, String currencyCode,
//                                                               String startDate, String endDate) {
//        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{date}", String.class, table, currencyCode,
//                startDate, endDate);
//    }
//
//    public Optional<String> getLastDaysRateForGivenCurrency(String table, String currencyCode, int topCount) {
//        return callGetMethod(CURRENCY_URL + "/rates/{table}/{code}/{topCount}/", String.class, table, currencyCode,
//                "last", topCount);
//    }
}
