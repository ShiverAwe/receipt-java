package space.shefer.receipt.platform.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import space.shefer.receipt.platform.core.ReceiptCoreConfiguration;

@EnableScheduling
@Import(ReceiptCoreConfiguration.class)
@SpringBootApplication
public class JobsApplication {
  public static void main(String[] args) {
    SpringApplication.run(JobsApplication.class, args);
  }
}
