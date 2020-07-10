package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fnssdk.dto.FnsLoginResponse;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.repository.UserProfileRepository;
import space.shefer.receipt.rest.dto.UserLoginDto;
import space.shefer.receipt.rest.dto.UserSignUpDto;

@Service
public class FnsUserService {

  private final UserProfileRepository userProfileRepository;
  private final FnsReceiptWebClient fnsReceiptWebClient;

  @Autowired
  public FnsUserService(UserProfileRepository userProfileRepository, FnsReceiptWebClient fnsReceiptWebClient) {
    this.userProfileRepository = userProfileRepository;
    this.fnsReceiptWebClient = fnsReceiptWebClient;
  }

  public void signUp(UserSignUpDto userSignUpDto) {
    fnsReceiptWebClient.signUp(userSignUpDto.getEmail(), userSignUpDto.getName(), userSignUpDto.getPhone());
  }

  public UserSignUpDto getUserByToken(String authHeader) {
    UserProfile userProfile = userProfileRepository.getByAccessToken(authHeader);
    if (userProfile == null) {
      return null;
    }
    UserSignUpDto userSignUpDto = new UserSignUpDto();
    userSignUpDto.setEmail(userProfile.getEmail());
    userSignUpDto.setName(userProfile.getName());
    userSignUpDto.setPhone(userProfile.getPhone());
    return userSignUpDto;
  }

  public void login(UserLoginDto userLoginDto) {
    FnsLoginResponse responseEntity = fnsReceiptWebClient.login(userLoginDto.getPhone(), userLoginDto.getPassword());
    if (responseEntity != null) {
      UserProfile userProfile = userProfileRepository.getByPhone(userLoginDto.getPhone());
      if (userProfile != null) {
        if (!userProfile.getEmail().equals(responseEntity.email) || (!userProfile.getName().equals(responseEntity.name))) {
          userProfile.setName(responseEntity.getName());
          userProfile.setEmail(responseEntity.getEmail());
          userProfileRepository.save(userProfile);
        }
      }
      else {
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setPhone(userLoginDto.getPhone());
        newUserProfile.setEmail(responseEntity.email);
        newUserProfile.setName(responseEntity.name);
        newUserProfile.setPassword(userLoginDto.getPassword());
        userProfileRepository.save(newUserProfile);
      }
    }
  }

}
