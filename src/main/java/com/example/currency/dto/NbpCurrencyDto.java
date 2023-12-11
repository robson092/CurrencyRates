package com.example.currency.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NbpCurrencyDto {
    private String currency;
    private String code;
    private List<NbpCurrencyRatesDto> rates = new ArrayList<>();

}
