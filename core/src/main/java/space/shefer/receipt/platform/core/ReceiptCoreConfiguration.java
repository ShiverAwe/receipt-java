package space.shefer.receipt.platform.core;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import space.shefer.receipt.fnssdk.config.EnableFnsSdk;

@EnableFnsSdk
@ComponentScan
@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan
@SpringBootConfiguration
public class ReceiptCoreConfiguration {
}
