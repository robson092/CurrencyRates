package com.example.currency.webclient;

import com.example.currency.dto.NbpCurrencyDto;
import com.example.currency.dto.NbpCurrencyRatesDto;
import com.example.currency.dto.NbpCurrencyRatesForDateRangeDto;
import com.example.currency.model.CurrencyRateDto;
import com.example.currency.model.CurrencyRateForDateRangeDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyClient {

    private static final String CURRENCY_URL = "http://api.nbp.pl/api/exchangerates/rates/c";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(CURRENCY_URL + url, responseType, objects);
    }

    private List<NbpCurrencyRatesForDateRangeDto> getNbpCurrencyRatesForDateRangeDto(NbpCurrencyDto nbpCurrencyDto) {
        List<NbpCurrencyRatesDto> rates = nbpCurrencyDto.getRates();
        List<NbpCurrencyRatesForDateRangeDto> ratesForDateRange = new ArrayList<>();
        rates.forEach(nbpCurrencyRatesDto -> ratesForDateRange
                .add(new NbpCurrencyRatesForDateRangeDto(
                        nbpCurrencyRatesDto.getEffectiveDate(), nbpCurrencyRatesDto.getBid(), nbpCurrencyRatesDto.getAsk())));
        return ratesForDateRange;
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
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("/{code}/{date}", NbpCurrencyDto.class,
                currencyCode, date);
        return CurrencyRateDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .effectiveDate(nbpCurrencyDto.getRates().get(0).getEffectiveDate())
                .sell(nbpCurrencyDto.getRates().get(0).getBid())
                .buy(nbpCurrencyDto.getRates().get(0).getAsk())
                .build();
    }
    public CurrencyRateForDateRangeDto getRateForGivenCurrencyInTimeRange(String currencyCode,
                                                                          String startDate, String endDate) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("/{code}/{startDate}/{endDate}",
                NbpCurrencyDto.class, currencyCode, startDate, endDate);
        return CurrencyRateForDateRangeDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .rates(getNbpCurrencyRatesForDateRangeDto(nbpCurrencyDto))
                .build();
    }

    public CurrencyRateForDateRangeDto getLastDaysRateForGivenCurrency(String currencyCode, int topCount) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("/{code}/last/{topCount}", NbpCurrencyDto.class, currencyCode,
                topCount);
        return CurrencyRateForDateRangeDto.builder()
                .currency(nbpCurrencyDto.getCurrency())
                .code(nbpCurrencyDto.getCode())
                .rates(getNbpCurrencyRatesForDateRangeDto(nbpCurrencyDto))
                .build();
    }
}
