package space.shefer.receipt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Builder
public class ReceiptMetaDto {

  @NotNull
  @JsonFormat(pattern = "yyyyMMdd'T'HHmmss")
  private ZonedDateTime dateTime;
  private int currency;
  private double sum;
  @Nullable
  private String merchantName;

}
