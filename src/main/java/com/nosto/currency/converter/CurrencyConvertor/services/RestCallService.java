package com.nosto.currency.converter.CurrencyConvertor.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestCallService {

  @Value("${exchange_rates.api_key_name}")
  private String apiKeyName;

  @Value("${exchange_rates.api_key_value}")
  private String apiKeyValue;

  public Response restRequest(String method, String url) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = buildRequest(method, url);
    return client.newCall(request).execute();
  }

  private Request buildRequest(String method, String url) {
    return new Request.Builder()
        .url(url)
        .addHeader(apiKeyName, apiKeyValue)
        .method(method, null)
        .build();
  }
}
