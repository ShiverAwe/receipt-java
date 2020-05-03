package space.shefer.receipt.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("space.shefer.receipt.vk")
public class VkConfiguration {

  @Bean
  public static VkApiClient vkApiClient() {
    TransportClient transportClient = new HttpTransportClient();
    return new VkApiClient(transportClient);
  }

  @Bean
  @SneakyThrows
  public static UserActor userActor(
    VkApiClient vkApiClient,
    VkApiCredentials vkApiCredentials
  ) {
    String code = "";

    UserAuthResponse authResponse = vkApiClient.oAuth()
      .userAuthorizationCodeFlow(
        vkApiCredentials.getAppId(),
        vkApiCredentials.getClientSecret(),
        vkApiCredentials.getRedirectUri(),
        code)
      .execute();

    return new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
  }


}
