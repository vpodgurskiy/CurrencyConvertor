package com.nosto.currency.converter.CurrencyConvertor.services;

import static com.nosto.currency.converter.CurrencyConvertor.constants.Constant.CONVERT_CURRENCY_URL;

import com.nosto.currency.converter.CurrencyConvertor.models.CurrencyDto;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyConverterService {

  private final ExchangeRatesService exchangeRates;

  @Value("${exchange_rates.url}")
  private String url;

  @SneakyThrows
  public CurrencyDto convertCurrency(String from, String to, String amount) {
    String requestUrl = buildUrl(String.format(CONVERT_CURRENCY_URL, to, from, amount));
    return exchangeRates.getData(CurrencyDto.class, requestUrl);
  }

  private String buildUrl(String parametrisedUrl) {
    return url + parametrisedUrl;
  }
}
