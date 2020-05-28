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


@RestController
public class UserController {

  private final FnsReceiptWebClient fnsReceiptService;

  @Autowired
  public UserController(FnsReceiptWebClient fnsReceiptService) {
    this.fnsReceiptService = fnsReceiptService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody UserLoginDto userLoginDto) {
    fnsReceiptService.login(userLoginDto.getPhone(), userLoginDto.getPassword());
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSignUpDto userSignUpDto) {
    fnsReceiptService.signUp(userSignUpDto.getEmail(), userSignUpDto.getName(), userSignUpDto.getPhone());
  }

  @RequestMapping(value = "/passwordRestore", method = RequestMethod.POST)
  public void passwordRestore(@RequestBody UserPasswordRestoreDto userPasswordRestoreDto) {
    fnsReceiptService.passwordRestore(userPasswordRestoreDto.getPhone());
  }

}
