package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.entity.UserProfile;
import space.shefer.receipt.rest.repository.UserProfileRepository;

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

  public String getAccessToken(String phone) {
    UserProfile userProfile = userProfileRepository.getByPhone(phone);
    if (userProfile != null) {
      return userProfile.getAccessToken();
    }
    return null;
  }

}
