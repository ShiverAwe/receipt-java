package space.shefer.receipt.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import space.shefer.receipt.fnssdk.config.EnableFnsSdk;

@EnableFnsSdk
@EnableScheduling
@SpringBootApplication
public class RestApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
}
