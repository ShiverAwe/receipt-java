package space.shefer.receipt.rest.dto;

import lombok.Data;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Data
public class ReceiptMetaDto {
  @Nullable
  private Long id;
  private LocalDateTime date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;
  private String provider;
  private ReceiptStatus status;
  private String place;

}
