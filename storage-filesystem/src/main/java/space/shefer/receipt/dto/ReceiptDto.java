package space.shefer.receipt.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class ReceiptDto {
  public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

  public static String id(ReceiptDto receiptDto) {
    return receiptDto.getMeta().getDateTime()
      .withZoneSameInstant(ZoneId.of("UTC"))
      .format(DATE_FORMATTER)
      + "_" + (long) receiptDto.getMeta().getSum();
  }

  private ReceiptMetaDto meta;
  private List<ReceiptItemDto> items;
  private Set<String> tags;
  private Map<String, String> other;

}
