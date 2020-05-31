package space.shefer.receipt.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSignUpDto {

  @Schema(
    required = true,
    description = "User email",
    example = "john.doe@example.com"
  )
  private String email;

  @Schema(
    required = true,
    description = "User full name",
    example = "John Doe"
  )
  private String name;

  @Schema(
    required = true,
    description = "Phone number",
    format = "+79XXXXXXXXX",
    example = "+79631234567"
  )
  private String phone;

}
