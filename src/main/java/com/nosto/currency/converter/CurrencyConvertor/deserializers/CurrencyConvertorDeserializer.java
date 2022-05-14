package com.nosto.currency.converter.CurrencyConvertor.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nosto.currency.converter.CurrencyConvertor.exceptions.ExchangeRatesError;
import com.nosto.currency.converter.CurrencyConvertor.models.CurrencyDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class CurrencyConvertorDeserializer extends StdDeserializer<CurrencyDto> {

  public CurrencyConvertorDeserializer() {
    this(null);
  }

  public CurrencyConvertorDeserializer(Class<?> vc) {
    super(vc);
  }

  @SneakyThrows
  @Override
  public CurrencyDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    CurrencyDto currencyModel = new CurrencyDto();
    Map<String, Object> body = objectMapper.readValue(node.toString(), HashMap.class);
    if (body.get("error") != null) {
      ExchangeRatesError error = objectMapper.convertValue(body.get("error"),
          ExchangeRatesError.class);
      currencyModel.setError(error);
      return currencyModel;
    }
    Map<String, String> query = (Map<String, String>) body.get("query");
    currencyModel.setCurrencyFrom(query.get("from"));
    currencyModel.setCurrencyTo(query.get("to"));
    currencyModel.setAmount((Integer.valueOf(String.valueOf(query.get("amount")))));
    currencyModel.setDate(LocalDate.parse(String.valueOf(body.get("date")),
        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    currencyModel.setMonetaryValue((Double) body.get("result"));
    currencyModel.setRate((Double) body.get("rate"));

    return currencyModel;
  }
}
