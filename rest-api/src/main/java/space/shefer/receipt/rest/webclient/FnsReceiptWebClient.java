package space.shefer.receipt.rest.webclient;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class FnsReceiptWebClient {

  private static final String HOST = "https://proverkacheka.nalog.ru:9999";
  @Value("${fns.login}")
  private String login;
  @Value("${fns.password}")
  private String password;

  public String get(String fn, String fd, String fp) {
    String uri = urlGet(fn, fd, fp);

    HttpHeaders headers = new HttpHeaders();
    headers.add("device-id", "");
    headers.add("device-os", "");
    headers.add("Authorization", getAuthHeader(login, password));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.exchange(
      URI.create(uri), HttpMethod.GET, requestEntity, String.class);

    HttpStatus statusCode = responseEntity.getStatusCode();

    return responseEntity.getBody();
  }

  private String getAuthHeader(String login, String password) {
    String plainCredentials = login + ":" + password;
    byte[] plainCredentialsBytes = plainCredentials.getBytes();
    byte[] base64CredentialsBytes = Base64.encodeBase64(plainCredentialsBytes);
    String base64Credentials = new String(base64CredentialsBytes);
    return "Basic " + base64Credentials;
  }

  private static String urlGet(String fn, String fd, String fp) {
    return HOST + "/v1/inns/*/kkts/*" +
      "/fss/" + fn +
      "/tickets/" + fd +
      "?fiscalSign=" + fp +
      "&sendToEmail=no";
  }

}
