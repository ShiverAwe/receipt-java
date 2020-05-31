package space.shefer.receipt.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import space.shefer.receipt.platform.core.ReceiptCoreConfiguration;

@OpenAPIDefinition(
  info = @Info(
    title = "Receipt Project REST-API documentation",
    contact = @Contact(
      name = "Vladimir Shefer",
      email = "vladimir.shefer@shefer.space",
      url = "https://cv.shefer.space"
    ),
    license = @License(
      name = "Apache 2.0",
      url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    ),
    version = "0.1"
  ),
  servers = {
    @Server(
      url = "https://receipt.shefer.space/api",
      description = "Production"
    )
  }
)
@Import(ReceiptCoreConfiguration.class)
@SpringBootApplication
public class RestApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
}
