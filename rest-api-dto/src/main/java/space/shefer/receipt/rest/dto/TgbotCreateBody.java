package space.shefer.receipt.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TgbotCreateBody {

  @Schema(
    required = true,
    description = "User phone number"
  )
  private String userId;

  @Schema(
    required = true,
    description = "Serialized json of receipt from FNS application"
  )
  private String receiptJson;

}
