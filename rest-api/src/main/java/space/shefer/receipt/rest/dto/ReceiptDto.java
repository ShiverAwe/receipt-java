package space.shefer.receipt.rest.dto;

import space.shefer.receipt.rest.entity.Receipt;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReceiptDto {
  private ReceiptMetaDto meta;
  private List<ReceiptItemDto> items;

  public static ReceiptDto of(Receipt receipt) {
    ReceiptDto result = new ReceiptDto();
    result.setMeta(ReceiptMetaDto.of(receipt));
    result.setItems(receipt.getItems().stream().map(ReceiptItemDto::of).collect(Collectors.toList()));
    return result;
  }
}
