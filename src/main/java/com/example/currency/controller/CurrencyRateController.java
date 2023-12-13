package com.example.currency.controller;

import com.example.currency.model.CurrencyRateDto;
import com.example.currency.model.CurrencyRateForDateRangeDto;
import com.example.currency.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{currencyCode}/{date}")
    public ResponseEntity<CurrencyRateDto> getCurrencyForDate(@PathVariable String currencyCode, @PathVariable String date) {
        return currencyRateService.getCurrencyRateForGivenDay(currencyCode, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{currencyCode}/{startDate}/{endDate}")
    public ResponseEntity<CurrencyRateForDateRangeDto> getCurrencyForTimeRange(@PathVariable String currencyCode, @PathVariable String startDate,
                                                                               @PathVariable String endDate) {
        return currencyRateService.getCurrencyRateForTimeRange(currencyCode, startDate, endDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{currencyCode}/last")
    public ResponseEntity<CurrencyRateForDateRangeDto> getCurrencyForLastCount(@PathVariable String currencyCode, @RequestParam("count") int count) {
        return currencyRateService.getLastCountOfCurrencyRate(currencyCode, count)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
