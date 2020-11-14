package space.shefer.receipt.platform.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user_profile")
public class UserProfile extends BaseUuidIdEntity {

  private String phone;

  private String email;

  private String password;

  private String accessToken;

}
