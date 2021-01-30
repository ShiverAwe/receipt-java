package space.shefer.receipt.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class ReceiptDto {
  public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

  public static String id(ReceiptDto receiptDto) {
    return receiptDto.getMeta().getDateTime()
      .withZoneSameInstant(ZoneId.of("UTC"))
      .format(DATE_FORMATTER);
  }

  @NotNull
  private ReceiptMetaDto meta;

  @NotNull
  private List<ReceiptItemDto> items = new ArrayList<>();

  @NotNull
  private Set<String> tags = new HashSet<>();
  
  @NotNull
  private Map<String, String> other = new HashMap<>();

}
