package space.shefer.receipt.rest.service;

import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.dto.MerchantSimpleName;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@Service
public class MerchantLogoService {

  @Nullable
  public String getUrlForImagePlace(String namePlace) {
    return Stream.of(MerchantSimpleName.values())
      .filter(merchant -> merchant.getValue().contains(namePlace.toUpperCase()))
      .findFirst()
      .map(MerchantSimpleName::getLogoUrl).orElse(null);
  }

}
