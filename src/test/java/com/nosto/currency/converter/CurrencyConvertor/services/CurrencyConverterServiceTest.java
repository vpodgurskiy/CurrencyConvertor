package com.nosto.currency.converter.CurrencyConvertor.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterServiceTest {

  @InjectMocks
  private CurrencyConverterService currencyConverterService;
  @Mock
  private ExchangeRatesService exchangeRates;

  @Test
  void convertCurrencyTest() {
    currencyConverterService.convertCurrency("USD", "EUR", "100");
    verify(exchangeRates, times(1)).getData(any(), any());
  }
}