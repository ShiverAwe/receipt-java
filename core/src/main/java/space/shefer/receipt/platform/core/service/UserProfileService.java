package space.shefer.receipt.platform.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Nullable;
import org.springframework.stereotype.Service;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.repository.UserProfileRepository;


import java.util.UUID;

@Service
public class UserProfileService {

  private final UserProfileRepository userProfileRepository;

  @Autowired
  public UserProfileService(UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }

  public UserProfile createOrUpdate(String phone, String password) {
    UserProfile userProfile = userProfileRepository.getByPhone(phone);
    if (userProfile == null) {
      UserProfile resultUser = new UserProfile();
      resultUser.setPassword(password);
      resultUser.setPhone(phone);
      resultUser.setAccessToken(UUID.randomUUID().toString());
      return userProfileRepository.save(resultUser);
    }
    if (!userProfile.getPassword().equals(password)) {
      userProfile.setPassword(password);
      userProfile.setPhone(phone);
      return userProfileRepository.save(userProfile);
    }
    return userProfile;
  }

  @Nullable
  public UserProfile getUserByToken(@Nullable String token) {
    if (token == null) {
      return null;
    }
    return userProfileRepository.getByAccessToken(token);
  }

}
