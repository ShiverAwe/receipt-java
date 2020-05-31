package space.shefer.receipt.platform.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import space.shefer.receipt.platform.core.entity.UserProfile;


public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

  @Query("SELECT up FROM UserProfile up WHERE up.phone = :phone")
  UserProfile getByPhone(@Param("phone") String phone);

  @Query("SELECT up FROM UserProfile up WHERE up.accessToken = :token")
  UserProfile getByAccessToken(@Param("token") String token);


}
