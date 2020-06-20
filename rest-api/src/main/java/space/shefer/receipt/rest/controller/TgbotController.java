package space.shefer.receipt.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.fnssdk.dto.FnsAppReceiptDto;
import space.shefer.receipt.platform.core.dto.ReceiptProvider;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.service.FnsReceiptService;
import space.shefer.receipt.rest.dto.TgbotCreateBody;

@Tag(name = "Telegram bot")
@RestController
@RequestMapping("tgbot")
public class TgbotController {

  private final FnsReceiptService receiptService;

  @Autowired
  public TgbotController(FnsReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @Operation(
    description = "Submit receipt from telegram-bot",
    responses = @ApiResponse(
      responseCode = "200",
      description = "Receipt successfully submitted",
      content = @Content(schema = @Schema(
        description = "The id of receipt which was created",
        example = "135"
      ))
    )
  )
  @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  public String create(@RequestBody TgbotCreateBody body) {
    FnsAppReceiptDto fnsAppReceiptDto = FnsAppReceiptDto.fromString(body.getReceiptJson());
    return receiptService
      .update(fnsAppReceiptDto, new Receipt(), ReceiptProvider.TGBOT_NALOG.name())
      .getId()
      .toString();
  }

}
