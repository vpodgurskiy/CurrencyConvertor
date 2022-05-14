package com.nosto.currency.converter.CurrencyConvertor.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosto.currency.converter.CurrencyConvertor.exceptions.ExchangeRatesError;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRatesService {

  private final RestCallService restCallService;
  private final ObjectMapper objectMapper;

  @SneakyThrows
  <T> T getData(Class<T> classType, String url) {
    T data = classType.getDeclaredConstructor().newInstance();
    try {
      Response response = restCallService.restRequest("GET", url);
      data = objectMapper.readValue(response.body().string(), classType);
    } catch (Exception e) {
      log.error("Failed to retrieve data from rest service cause: {}", e.getMessage());
    }
    return data;
  }
}
