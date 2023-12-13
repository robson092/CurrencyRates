package com.example.currency.model;

import com.example.currency.dto.NbpCurrencyRatesForDateRangeDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CurrencyRateForDateRangeDto {

    private String currency;
    private String code;
    private List<NbpCurrencyRatesForDateRangeDto> rates;
}
