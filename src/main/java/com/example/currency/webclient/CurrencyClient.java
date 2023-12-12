package com.example.currency.webclient;

import com.example.currency.dto.NbpCurrencyDto;
import com.example.currency.model.CurrencyRateDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyClient {

    private static final String CURRENCY_URL = "http://api.nbp.pl/api/exchangerates/rates/c";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(CURRENCY_URL + url, responseType, objects);
    }

    public CurrencyRateDto getRateForGivenCurrency(String currencyCode) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("/{code}", NbpCurrencyDto.class, currencyCode);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }

    public CurrencyRateDto getRateForGivenCurrencyInGivenDay(String currencyCode, String date) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod(CURRENCY_URL + "/{code}/{date}", NbpCurrencyDto.class,
                currencyCode, date);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }

    public CurrencyRateDto getTodayRateForGivenCurrency(String currencyCode) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod(CURRENCY_URL + "/{code}/", NbpCurrencyDto.class,
                currencyCode, "today");
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }
//
    public CurrencyRateDto getRateForGivenCurrencyInTimeRange(String currencyCode,
                                                               String startDate, String endDate) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod(CURRENCY_URL + "/{code}/{date}", NbpCurrencyDto.class, currencyCode,
                startDate, endDate);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }
//
    public CurrencyRateDto getLastDaysRateForGivenCurrency(String currencyCode, int topCount) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod(CURRENCY_URL + "/{code}/{topCount}/", NbpCurrencyDto.class, currencyCode,
                "last", topCount);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }
}
