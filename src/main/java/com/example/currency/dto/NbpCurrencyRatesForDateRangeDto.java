package com.example.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NbpCurrencyRatesForDateRangeDto {

    private String effectiveDate;
    private String sell;
    private String buy;
}
