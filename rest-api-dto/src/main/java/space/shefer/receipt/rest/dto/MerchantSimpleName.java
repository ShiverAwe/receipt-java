package space.shefer.receipt.rest.dto;

public enum MerchantSimpleName {
  KARUSEL("Карусель"),
  LENTA("Лента"),
  PEREKROSTOK("Перекресток"),
  OKEY("Okey"),
  DIXY("Дикси");

  private final String value;

  MerchantSimpleName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
