package com.github.shiverawe.dto;

import com.github.shiverawe.entity.Receipt;
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
