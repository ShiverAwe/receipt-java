package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.dto.FnsAppReceiptDto;
import space.shefer.receipt.platform.core.dto.ReceiptProvider;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.service.FnsReceiptService;
import space.shefer.receipt.rest.dto.TgbotCreateBody;

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
    FnsAppReceiptDto fnsAppReceiptDto = FnsAppReceiptDto.fromString(body.getReceiptJson());
    return receiptService
      .update(fnsAppReceiptDto, new Receipt(), ReceiptProvider.TGBOT_NALOG.name())
      .getId()
      .toString();
  }

}
