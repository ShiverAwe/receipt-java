package space.shefer.receipt.platform.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserProfile)) return false;

    UserProfile that = (UserProfile) o;

    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
