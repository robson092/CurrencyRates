package com.example.currency.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CurrencyRateDto {

    private String currency;
    private String code;
    private String effectiveDate;
    private String sell;
    private String buy;
}
