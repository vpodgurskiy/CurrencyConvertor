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

@JsonDeserialize(using = CurrencyConvertorDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CurrencyDto {

  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate date;
  Double rate;
  Integer amount;
  String currencyFrom;
  String currencyTo;
  Double monetaryValue;
  ExchangeRatesError error;

  @JsonProperty("monetaryValue")
  public Double getMonetaryValue() {
    return monetaryValue;
  }

  @JsonProperty("result")
  public Double setMonetaryValue(Double monetaryValue) {
    return this.monetaryValue = monetaryValue;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @JsonProperty("currencyFrom")
  public String getCurrencyFrom() {
    return currencyFrom;
  }

  @JsonProperty("from")
  public void setCurrencyFrom(String currencyFrom) {
    this.currencyFrom = currencyFrom;
  }

  @JsonProperty("currencyTo")
  public String getCurrencyTo() {
    return currencyTo;
  }

  @JsonProperty("to")
  public void setCurrencyTo(String currencyTo) {
    this.currencyTo = currencyTo;
  }

  public ExchangeRatesError getError() {
    return error;
  }

  public void setError(ExchangeRatesError error) {
    this.error = error;
  }
}
