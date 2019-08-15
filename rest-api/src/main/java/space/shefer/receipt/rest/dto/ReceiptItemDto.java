package space.shefer.receipt.rest.dto;

import space.shefer.receipt.rest.entity.Item;
import lombok.Data;

@Data
public class ReceiptItemDto {
  private String text;
  private Double price;
  private Double amount;

  public static ReceiptItemDto of(Item item) {
    ReceiptItemDto result = new ReceiptItemDto();
    result.setAmount(item.getAmount());
    result.setPrice(item.getPrice());
    result.setText(item.getText());
    return result;
  }
}
