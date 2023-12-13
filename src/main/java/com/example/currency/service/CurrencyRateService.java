package com.example.currency.service;

import com.example.currency.model.CurrencyRateDto;
import com.example.currency.model.CurrencyRateForDateRangeDto;
import com.example.currency.webclient.CurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateService {

    private final CurrencyClient currencyClient;

    public Optional<CurrencyRateDto> getCurrencyRate(String currencyCode) {
        return Optional.ofNullable(currencyClient.getRateForGivenCurrency(currencyCode));
    }

    public Optional<CurrencyRateDto> getCurrencyRateForGivenDay(String currencyCode, String date) {
        return Optional.ofNullable(currencyClient.getRateForGivenCurrencyInGivenDay(currencyCode, date));
    }

    public Optional<CurrencyRateForDateRangeDto> getCurrencyRateForTimeRange(String currencyCode, String startDate, String endDate) {
        return Optional.ofNullable(currencyClient.getRateForGivenCurrencyInTimeRange(currencyCode, startDate, endDate));
    }

    public Optional<CurrencyRateForDateRangeDto> getLastCountOfCurrencyRate(String currencyCode, int topCount) {
        return Optional.ofNullable(currencyClient.getLastDaysRateForGivenCurrency(currencyCode, topCount));
    }
}
