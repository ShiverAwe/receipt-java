package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.rest.service.ReceiptService;

@RestController
@RequestMapping("tgbot")
public class TelegramBotController {

  private final ReceiptService receiptService;

  @Autowired
  public TelegramBotController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public Long create(@RequestBody Object query) {
    throw new UnsupportedOperationException();
  }

}
