package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fnssdk.dto.UserResponseLoginFnsDto;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.repository.UserProfileRepository;
import space.shefer.receipt.rest.dto.UserLoginDto;
import space.shefer.receipt.rest.dto.UserSignUpDto;

import java.util.Objects;

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
    UserResponseLoginFnsDto responseEntity = fnsReceiptWebClient.login(userLoginDto.getPhone(), userLoginDto.getPassword());
    if (responseEntity != null) {
      UserProfile userProfile = userProfileRepository.getByPhone(userLoginDto.getPhone());
      if (userProfile != null && userProfile.getUpdatedAt() == null) {
        userProfile.setName(responseEntity.getName());
        userProfile.setEmail(responseEntity.getEmail());
        userProfileRepository.save(userProfile);
      }
    }
  }

}
