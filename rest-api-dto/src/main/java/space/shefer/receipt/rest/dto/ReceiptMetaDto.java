package space.shefer.receipt.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Data
public class ReceiptMetaDto {
  @Schema(
    title = "Receipt identifier",
    minimum = "0",
    required = true,
    example = "135"
  )
  private Long id;

  @Schema(
    required = true,
    format = "yyyy-MM-dd'T'HH:mm:ss",
    example = "2017-06-05T18:01:08"
  )
  private LocalDateTime date;

  @Schema(
    required=true,
    description = "ФН, Фискальный Накопитель",
    format = "numeric",
    minLength = 16,
    maxLength = 16,
    example = "9251440300048237"
  )
  private String fn;
  private String fd;
  private String fp;
  private Double sum;
  private ReceiptStatus status;
  private String place;

  @Schema(example = "Торговый дом \"Реалъ\"")
  @Nullable
  private String merchantName;
  @Schema(
    format = "numeric",
    minLength = 10,
    maxLength = 10,
    example = "7805506339"
  )
  @Nullable
  private String merchantInn;
  @Nullable
  private String merchantPlaceAddress;
  @Nullable
  private String merchantLogoUrl;
}
