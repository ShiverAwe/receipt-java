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

  public UserProfile createOrUpdate(String phone, String password) {
    UserProfile userProfile = userProfileRepository.getByPhone(phone);
    UserProfile resultUser = new UserProfile();
    if (userProfile == null) {
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
    return resultUser;
  }
}
