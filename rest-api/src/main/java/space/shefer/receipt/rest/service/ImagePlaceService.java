package space.shefer.receipt.rest.service;

import space.shefer.receipt.rest.dto.ReceiptImagePlace;
import java.util.Arrays;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImagePlaceService {

// Магазины с ссылками
  private Map<ReceiptImagePlace, String> setStoresAndImages() {
    Map<ReceiptImagePlace, String> storesAndImages = new HashMap<>();
    storesAndImages.put(ReceiptImagePlace.OKEY, "https://images.app.goo.gl/dQJ841uupaAueuyc7");
    storesAndImages.put(ReceiptImagePlace.LENTA, "https://images.app.goo.gl/zq5jQrQ6LTbRe9WX7");
    storesAndImages.put(ReceiptImagePlace.KARUSEL, "https://images.app.goo.gl/qsXCpg2eihLbwFqC7");
    storesAndImages.put(ReceiptImagePlace.PEREKRESTOK, "https://images.app.goo.gl/L2MMepenJQeqM2un6");
    storesAndImages.put(ReceiptImagePlace.DIXY, "https://images.app.goo.gl/NAfEjeTZHRV6DDBYA");
    return storesAndImages;
  }

// при получении полного имени магазина беру посленее слово без всяких ООО
  public String getStoreDefinition(String namePlace) {
    if (namePlace == null) {
      return null;
    }
    namePlace = namePlace.trim();
    if (!namePlace.contains(" ")) {
      return namePlace;
    }

    List<String> catNames = new ArrayList<String>((Arrays.asList(namePlace.split(" "))));
    return catNames.get(catNames.size() - 1);
  }

// Ищу есть ли у нас такой магаз
  public String getUrlForImagePlace(String namePlace) {
    if (namePlace == null) return null;
    for (Map.Entry<ReceiptImagePlace, String> receiptImagePlace : setStoresAndImages().entrySet()) {
      if (receiptImagePlace.getKey().toString().equalsIgnoreCase(namePlace)) {
        return receiptImagePlace.getValue();
      }
    }
    return null;
  }
//Вызываю методы
  public String getFinalReply(String namePlace) {
    return getUrlForImagePlace(getStoreDefinition(namePlace));
  }

  public static void main(String[] args) {
    ImagePlaceService imagePlaceService = new ImagePlaceService();
    System.out.println(imagePlaceService.getFinalReply("Lenta"));
  }

}
