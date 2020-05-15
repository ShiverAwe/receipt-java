package space.shefer.receipt.rest.service;

import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.dto.MerchantSimpleName;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
public class MerchantLogoService {

  @Nullable
  public String getUrlForImagePlace(String namePlace) {
    String url;
    // We must to use toAUpperCase method for namePlace because method contains not contain ignoreCase method.
    try {
      url = Stream.of(MerchantSimpleName.values())
        .filter(merchant -> merchant.getValue().contains(namePlace.toUpperCase()))
        .findFirst()
        .map(MerchantSimpleName::getUrl).get();
    }
    catch (NoSuchElementException e) {
      return null;
    }

    return url;
  }

  public static void main(String[] args) {
    MerchantLogoService merchantLogoService = new MerchantLogoService();
    System.out.println(merchantLogoService.getUrlForImagePlace("лента"));
  }

}
