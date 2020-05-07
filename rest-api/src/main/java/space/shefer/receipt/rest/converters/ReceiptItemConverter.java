package space.shefer.receipt.rest.converters;

import space.shefer.receipt.rest.dto.ReceiptItemDto;
import space.shefer.receipt.platform.core.entity.Item;

public class ReceiptItemConverter {

  public static ReceiptItemDto toDto(Item item) {
    ReceiptItemDto result = new ReceiptItemDto();
    result.setReceiptId(item.getReceipt().getId());
    result.setAmount(item.getAmount());
    result.setPrice(item.getPrice());
    result.setText(item.getText());
    return result;
  }

}
