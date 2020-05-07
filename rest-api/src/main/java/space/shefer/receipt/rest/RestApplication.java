package space.shefer.receipt.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import space.shefer.receipt.platform.core.ReceiptCoreConfiguration;

@Import(ReceiptCoreConfiguration.class)
@SpringBootApplication
public class RestApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
}
