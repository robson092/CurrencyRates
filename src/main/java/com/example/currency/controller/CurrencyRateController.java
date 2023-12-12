package com.example.currency.controller;

import com.example.currency.model.CurrencyRateDto;
import com.example.currency.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @GetMapping("/{currencyCode}")
    public ResponseEntity<CurrencyRateDto> getCurrency(@PathVariable String currencyCode) {
        return currencyRateService.getCurrencyRate(currencyCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
