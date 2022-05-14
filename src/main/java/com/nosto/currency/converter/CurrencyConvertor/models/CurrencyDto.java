package com.nosto.currency.converter.CurrencyConvertor.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nosto.currency.converter.CurrencyConvertor.deserializers.CurrencyConvertorDeserializer;
import com.nosto.currency.converter.CurrencyConvertor.exceptions.ExchangeRatesError;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize(using = CurrencyConvertorDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CurrencyDto {

  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate date;
  Double rate;
  Integer amount;
  @JsonProperty("from")
  String currencyFrom;
  @JsonProperty("to")
  String currencyTo;
  @JsonProperty("result")
  Double monetaryValue;
  ExchangeRatesError error;
}
