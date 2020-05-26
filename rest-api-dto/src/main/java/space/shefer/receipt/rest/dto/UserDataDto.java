package space.shefer.receipt.rest.dto;

import lombok.Data;

@Data
public class UserDataDto {
  private String login;
  private String password;
  private String phone;
  private String email;
  private String name;
}
