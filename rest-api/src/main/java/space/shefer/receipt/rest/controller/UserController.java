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
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.rest.converters.ReceiptMetaConverter;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.dto.UserLoginDto;
import space.shefer.receipt.rest.dto.UserPasswordRestoreDto;
import space.shefer.receipt.rest.dto.UserSignUpDto;

import javax.validation.Valid;


@RestController
public class UserController {

  private final FnsReceiptWebClient fnsReceiptWebClient;
  private final UserProfileService userProfileService;

  @Autowired
  public UserController(FnsReceiptWebClient fnsReceiptWebClient, UserProfileService userProfileService) {
    this.fnsReceiptWebClient = fnsReceiptWebClient;
    this.userProfileService = userProfileService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  public String login(@RequestBody UserLoginDto userLoginDto) {
    fnsReceiptWebClient.login(userLoginDto.getPhone(), userLoginDto.getPassword());
    UserProfile userProfile = userProfileService.createOrUpdate(userLoginDto.getPhone(), userLoginDto.getPassword());
    return userProfile.getAccessToken();
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSignUpDto userSignUpDto) {
    fnsReceiptWebClient.signUp(userSignUpDto.getEmail(), userSignUpDto.getName(), userSignUpDto.getPhone());
  }

  @RequestMapping(value = "/users/me", method = RequestMethod.GET)
  public ReceiptMetaDto getInfoFromToken(@Nullable @RequestHeader("Authorization") String authHeader) {
  return new ReceiptMetaDto();
  }

  @RequestMapping(value = "/passwordRestore", method = RequestMethod.POST)
  public void passwordRestore(@RequestBody UserPasswordRestoreDto userPasswordRestoreDto) {
    fnsReceiptWebClient.passwordRestore(userPasswordRestoreDto.getPhone());
  }


}
