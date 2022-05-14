package com.nosto.currency.converter.CurrencyConvertor.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosto.currency.converter.CurrencyConvertor.constants.Constant;
import com.nosto.currency.converter.CurrencyConvertor.models.CurrencyDto;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.http.RealResponseBody;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import com.squareup.okhttp.Response;
import okio.BufferedSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesServiceTest {

  @InjectMocks
  private ExchangeRatesService exchangeRatesService;
  @Mock
  private RestCallService restCallService;
  @Mock
  private ObjectMapper objectMapper;


  @SneakyThrows
  @Test
  public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {

    CurrencyDto expectedValue = buildCurrencyModel(new ObjectMapper().readValue("{\n"
        + "  \"date\": \"2022-05-12\",\n"
        + "  \"info\": {\n"
        + "    \"rate\": 0.96055,\n"
        + "    \"timestamp\": 1652358136\n"
        + "  },\n"
        + "  \"query\": {\n"
        + "    \"amount\": 10,\n"
        + "    \"from\": \"USD\",\n"
        + "    \"to\": \"EUR\"\n"
        + "  },\n"
        + "  \"result\": 9.6055,\n"
        + "  \"success\": true\n"
        + "}", HashMap.class));

    when(restCallService.restRequest("GET", "url"))
          .thenReturn(buildResponse());

    when(objectMapper.readValue(anyString(), eq(CurrencyDto.class))).thenReturn(expectedValue);
    CurrencyDto actualValue = exchangeRatesService.getData(CurrencyDto.class, "url");
    Assertions.assertEquals(expectedValue, actualValue);
  }

  private CurrencyDto buildCurrencyModel(Map<String, Object> body) {
    CurrencyDto currencyModel = new CurrencyDto();
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

  private Response buildResponse() {
    Request mockRequest = new Request.Builder()
        .url("https://test.com/")
        .build();
    return new Response.Builder()
        .request(mockRequest)
        .protocol(Protocol.HTTP_2)
        .code(200) // status code
        .message("")
        .body(ResponseBody.create(MediaType.parse("application/json"), "{}"))
        .build();
  }
}