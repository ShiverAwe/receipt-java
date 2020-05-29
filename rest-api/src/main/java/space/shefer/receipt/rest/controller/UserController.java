package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.rest.dto.UserLoginDto;
import space.shefer.receipt.rest.dto.UserPasswordRestoreDto;
import space.shefer.receipt.rest.dto.UserSignUpDto;
import space.shefer.receipt.rest.service.UserProfileService;


@RestController
public class UserController {

  private final FnsReceiptWebClient fnsReceiptWebClient;
  private final UserProfileService userProfileService;

  @Autowired
  public UserController(FnsReceiptWebClient fnsReceiptWebClient, UserProfileService userProfileService) {
    this.fnsReceiptWebClient = fnsReceiptWebClient;
    this.userProfileService = userProfileService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody UserLoginDto userLoginDto) {
    fnsReceiptWebClient.login(userLoginDto.getPhone(), userLoginDto.getPassword());
    userProfileService.createOrUpdate(userLoginDto.getPhone(), userLoginDto.getPassword()).getAccessToken();
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSignUpDto userSignUpDto) {
    fnsReceiptWebClient.signUp(userSignUpDto.getEmail(), userSignUpDto.getName(), userSignUpDto.getPhone());
  }

  @RequestMapping(value = "/passwordRestore", method = RequestMethod.POST)
  public void passwordRestore(@RequestBody UserPasswordRestoreDto userPasswordRestoreDto) {
    fnsReceiptWebClient.passwordRestore(userPasswordRestoreDto.getPhone());
  }


}
