package space.shefer.receipt.dto;

import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class ReceiptMetaDto {

  @NotNull
  private ZonedDateTime dateTime;
  private int currency;
  private double sum;
  @Nullable
  private String merchantName;

}
