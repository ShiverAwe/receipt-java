package space.shefer.receipt.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.shefer.receipt.rest.entity.UserProfile;


public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

  @Query("SELECT up FROM UserProfile up WHERE up.phone = :phone")
  UserProfile getByPhone(String phone);

}
