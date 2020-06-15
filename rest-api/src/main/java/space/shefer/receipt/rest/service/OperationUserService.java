package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.repository.UserProfileRepository;
import space.shefer.receipt.rest.dto.UserSignUpDto;

@Service
public class OperationUserService {

  private final UserProfileRepository userProfileRepository;
  private final FnsReceiptWebClient fnsReceiptWebClient;

  @Autowired
  public OperationUserService(UserProfileRepository userProfileRepository, FnsReceiptWebClient fnsReceiptWebClient) {
    this.userProfileRepository = userProfileRepository;
    this.fnsReceiptWebClient = fnsReceiptWebClient;
  }

  public void signUp(UserSignUpDto userSignUpDto) {
    UserProfile userProfile = userProfileRepository.getByPhone(userSignUpDto.getPhone());
    if (fnsReceiptWebClient.signUp(userSignUpDto.getName(), userSignUpDto.getName(), userSignUpDto.getPhone())) {
      userProfile.setPhone(userSignUpDto.getPhone());
      userProfile.setEmail(userSignUpDto.getEmail());
      userProfile.setName(userSignUpDto.getName());
      userProfileRepository.save(userProfile);
    }

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

}
