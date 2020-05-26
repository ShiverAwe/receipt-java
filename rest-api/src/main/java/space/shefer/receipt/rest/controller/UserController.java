package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient;
import space.shefer.receipt.rest.dto.UserDataDto;


@RestController
public class UserController {

  private final FnsReceiptWebClient fnsReceiptService;

  @Autowired
  public UserController(FnsReceiptWebClient fnsReceiptService) {
    this.fnsReceiptService = fnsReceiptService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void login(@RequestBody UserDataDto userDataDto) {
    fnsReceiptService.login(userDataDto.getLogin(), userDataDto.getPassword());
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public void signUp(@RequestBody UserDataDto userDataDto) {
    fnsReceiptService.signUp(userDataDto.getEmail(), userDataDto.getName(), userDataDto.getPhone());
  }

  @RequestMapping(value = "/passwordRestore", method = RequestMethod.GET)
  public void passwordRestore(@RequestBody UserDataDto userDataDto) {
    fnsReceiptService.passwordRestore(userDataDto.getPhone());
  }

}
