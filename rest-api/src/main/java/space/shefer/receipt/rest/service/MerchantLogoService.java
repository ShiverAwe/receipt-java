package space.shefer.receipt.rest.service;

import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.module.MerchantSimpleName;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@Service
public class MerchantLogoService {

  @Nullable
  public String getUrlForImagePlace(String merchantFullName) {
    return Stream.of(MerchantSimpleName.values())
      .filter(merchant -> merchantFullName.toUpperCase().contains(merchant.getValue()))
      .findFirst()
      .map(MerchantSimpleName::getLogoUrl).orElse(null);
  }

}
