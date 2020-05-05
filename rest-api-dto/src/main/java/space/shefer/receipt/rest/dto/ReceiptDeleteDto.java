package space.shefer.receipt.rest.dto;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class ReceiptDeleteDto {

  @Nullable
  private ReceiptStatus status;
  private Long id;

}
