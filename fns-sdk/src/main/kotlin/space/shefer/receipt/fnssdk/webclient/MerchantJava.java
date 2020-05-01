package space.shefer.receipt.fnssdk.webclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import space.shefer.receipt.fnssdk.primainform.MerchantSuggestion;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MerchantJava {


  public String get_merchant_from_prima_inform_or_none(String inn) {
    RestTemplate restTemplate = new RestTemplate();

    URI fooResourceUrl = URI.create("https://www.prima-inform.ru/utils/search?query=" + inn);
    ResponseEntity<MerchantSuggestion> response = restTemplate
      .exchange(fooResourceUrl, HttpMethod.POST, HttpEntity.EMPTY, MerchantSuggestion.class);

    List<Map<String, String>> merchant_row = response.getBody().suggestions
      .stream()
      .filter(n -> n.data.getInn().equals(inn))
      .map(n -> {
        Map<String, String> map = new HashMap<>();
        map.put("inn", n.data.getInn());
        map.put("address", n.data.getAddress());
        map.put("name", n.value);
        return map;
      }).collect(Collectors.toList());

    return "lk";

  }

//  RestTemplate restTemplate = new RestTemplate();
//  HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
//  ResponseEntity<Foo> response = restTemplate
//    .exchange(fooResourceUrl, HttpMethod.POST, request, Foo.class);

}
