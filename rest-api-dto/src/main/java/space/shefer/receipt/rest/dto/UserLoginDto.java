package space.shefer.receipt.rest.dto;

import lombok.Data;

@Data
public class UserLoginDto {
  private String phone;
  private String password;
}
