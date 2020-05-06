package space.shefer.receipt.rest.dto;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class ReceiptCreateNewFormatDto {

  private ReceiptStatus status;
  private Long id;

}
