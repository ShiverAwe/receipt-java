package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.entity.UserProfile;
import space.shefer.receipt.rest.repository.UserProfileRepository;

@Service
public class UserProfileService {

  private final UserProfileRepository userProfileRepository;

  @Autowired
  public UserProfileService(UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }

  public void saveCorrectUserData(String phone, String password) {
    UserProfile userProfile = userProfileRepository.getByPhone(phone);
    if (userProfile == null) {
      UserProfile resultUser = new UserProfile();
      resultUser.setPassword(password);
      resultUser.setPhone(phone);
      userProfileRepository.save(resultUser);
    }
    else {
      if (!userProfile.getPassword().equals(password)) {
        userProfile.setPassword(password);
        userProfileRepository.save(userProfile);
      }
    }
  }
}
