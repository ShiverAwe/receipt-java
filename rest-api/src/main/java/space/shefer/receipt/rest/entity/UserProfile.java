package space.shefer.receipt.rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user_profile")
public class UserProfile extends BaseUuidIdEntity {

  @Column(name = "phone", nullable = false, unique = true)
  private String phone;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "fns_request_count")
  private int fnsRequestCount = 0;

  @Column(name = "access_token")
  private String accessToken;

}
