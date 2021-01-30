package space.shefer.receipt.dto;

import lombok.Data;

@Data
public class ReceiptItemDto {

  private String name;
  private double amount;
  private double price;
  private double sum;

}
