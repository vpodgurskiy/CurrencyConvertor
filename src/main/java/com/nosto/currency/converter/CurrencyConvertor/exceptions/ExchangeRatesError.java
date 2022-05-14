package com.nosto.currency.converter.CurrencyConvertor.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRatesError {

  private String code;
  private String message;

  @Override
  public String toString() {
    return "Failed to get data from service {" +
        "code='" + code + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
