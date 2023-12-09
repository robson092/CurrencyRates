package com.example.currency.controller;

import com.example.currency.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @GetMapping("/rates")
    public ResponseEntity<String> getCurrency() {
        return currencyRateService.getCurrencyRate()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
