package com.example.currency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class NbpCurrencyRatesDto {

    private String effectiveDate;
    private String bid;
    private String ask;

}
