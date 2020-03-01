package space.shefer.receipt.rest.dto;

import lombok.Data;

@Data
public class ReceiptItemDto {
  private Long receiptId;
  private String text;
  private Double price;
  private Double amount;
}
