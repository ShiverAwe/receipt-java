package space.shefer.receipt.rest.dto;

import lombok.Data;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;

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
  private ReceiptStatus status;
  private String place;
  @Nullable
  private String merchantName;
  @Nullable
  private String merchantInn;
  @Nullable
  private String merchantPlaceAddress;
  @Nullable
  private String merchantLogoUrl;
}
