package space.shefer.receipt.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserPasswordRestoreDto {

  @Schema(
    required = true,
    description = "Phone number",
    format = "+79XXXXXXXXX",
    example = "+79631234567"
  )
  private String phone;

}
