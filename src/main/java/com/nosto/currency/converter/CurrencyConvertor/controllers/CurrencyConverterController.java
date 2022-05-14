package com.nosto.currency.converter.CurrencyConvertor.controllers;

import com.nosto.currency.converter.CurrencyConvertor.models.CurrencyDto;
import com.nosto.currency.converter.CurrencyConvertor.services.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyConverterController {

  private final CurrencyConverterService currencyConverterService;

  @GetMapping("/convertCurrency")
  public CurrencyDto getMonetaryValue(@RequestParam("sourceCurrency") String sourceCurrency,
                                 @RequestParam("targetCurrency") String targetCurrency,
                                 @RequestParam("amount") String amount) {
    return currencyConverterService.convertCurrency(sourceCurrency, targetCurrency, amount);
  }
}
