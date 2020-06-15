package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.rest.dto.UserLoginDto;
import space.shefer.receipt.rest.dto.UserPasswordRestoreDto;
import space.shefer.receipt.rest.dto.UserSignUpDto;
import space.shefer.receipt.rest.service.OperationUserService;


@RestController
public class UserController {

  private final FnsReceiptWebClient fnsReceiptWebClient;
  private final UserProfileService userProfileService;
  private final OperationUserService operationUserService;

  @Autowired
  public UserController(FnsReceiptWebClient fnsReceiptWebClient, UserProfileService userProfileService, OperationUserService operationUserService) {
    this.fnsReceiptWebClient = fnsReceiptWebClient;
    this.userProfileService = userProfileService;
    this.operationUserService = operationUserService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  public String login(@RequestBody UserLoginDto userLoginDto) {
    operationUserService.login(userLoginDto);
    UserProfile userProfile = userProfileService.createOrUpdate(userLoginDto.getPhone(), userLoginDto.getPassword());
    return userProfile.getAccessToken();
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSignUpDto userSignUpDto) {
    operationUserService.signUp(userSignUpDto);
  }

  @RequestMapping(value = "/users/me", method = RequestMethod.GET)
  public UserSignUpDto getInfoByToken(@Nullable @RequestHeader("Authorization") String authHeader) {
    if (authHeader != null) {
      return operationUserService.getUserByToken(authHeader.substring(authHeader.indexOf(" ") + 1));
    }
    return null;
  }

  @RequestMapping(value = "/passwordRestore", method = RequestMethod.POST)
  public void passwordRestore(@RequestBody UserPasswordRestoreDto userPasswordRestoreDto) {
    fnsReceiptWebClient.passwordRestore(userPasswordRestoreDto.getPhone());
  }


}
