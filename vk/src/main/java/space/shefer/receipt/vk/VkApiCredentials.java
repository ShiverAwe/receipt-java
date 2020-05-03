package space.shefer.receipt.vk;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class VkApiCredentials {

  @Value("vk.app.appId")
  private int appId;

  @Value("vk.app.clientSecret")
  private String clientSecret;

  @Value("vk.app.redirectUri")
  private String redirectUri;

}
