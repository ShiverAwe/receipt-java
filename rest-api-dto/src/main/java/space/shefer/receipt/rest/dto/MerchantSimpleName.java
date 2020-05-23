package space.shefer.receipt.rest.dto;

public enum MerchantSimpleName {
  KARUSEL("КАРУСЕЛЬ", "https://logosklad.ru/photo/logos/111/1458302934.jpg#.Xskw49d9YMs.link"),
  LENTA("ЛЕНТА", "https://irecommend.ru/content/kompyuternaya-programma-mobilnoe-prilozhenie-gipermarkety-lenta#&gid=gallery_node3567990field_product_images&pid=1"),
  PEREKRESTOK("ПЕРЕКРЕСТОК", "https://logosklad.ru/photo/logos/616/1582597280.jpg#.XskxMmZsY7g.link"),
  OKEY("О’КЕЙ", "https://logosklad.ru/UserFiles/image/okey/okey-logo-old.png#.XskxWWjaeWU.link"),
  DIXY("ДИКСИ", "https://akcionnik.ru/wp-content/uploads/2019/03/Logotip-Diksi.jpg"),
  REAL("РЕАЛЪ", "https://sun9-70.userapi.com/c604816/v604816859/276f2/HBdUQrpnKvc.jpg");

  private final String value;
  private final String logoUrl;

  MerchantSimpleName(String value, String logoUrl) {
    this.value = value;
    this.logoUrl = logoUrl;
  }

  public String getValue() {
    return value;
  }

  public String getLogoUrl() {
    return logoUrl;
  }
}
