package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.dto.FnsReceiptDto;
import space.shefer.receipt.rest.dto.ReceiptProvider;
import space.shefer.receipt.rest.dto.TgbotCreateBody;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.FnsReceiptService;

@RestController
@RequestMapping("tgbot")
public class TgbotController {

  private final FnsReceiptService receiptService;

  @Autowired
  public TgbotController(FnsReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(@RequestBody TgbotCreateBody body) {
    FnsReceiptDto fnsReceiptDto = FnsReceiptDto.fromString(body.getReceiptJson());
    return receiptService
      .update(fnsReceiptDto, new Receipt(), ReceiptProvider.TGBOT_NALOG.name())
      .getId()
      .toString();
  }

}
