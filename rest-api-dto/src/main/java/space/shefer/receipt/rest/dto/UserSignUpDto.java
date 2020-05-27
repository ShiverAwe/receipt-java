package space.shefer.receipt.rest.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
  private String email;
  private String name;
  private String phone;
}
