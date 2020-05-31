package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.platform.core.entity.UserProfile;
import space.shefer.receipt.platform.core.service.UserProfileService;
import space.shefer.receipt.rest.converters.ReceiptMetaConverter;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.rest.service.ReceiptService;

@RestController
public class ReceiptCreateController {

  private final ReceiptService receiptService;
  private final UserProfileService userProfileService;

  @Autowired
  public ReceiptCreateController(ReceiptService receiptService, UserProfileService userProfileService) {
    this.receiptService = receiptService;
    this.userProfileService = userProfileService;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ReceiptMetaDto create(@RequestBody ReceiptCreateDto query,
                               @Nullable @RequestHeader("Authorization") String authHeader) {
    UserProfile userProfile = null;
    if (authHeader != null) {
      userProfile = userProfileService.getUserByToken(authHeader.substring(authHeader.indexOf(" ") + 1));
    }
    Receipt receipt = receiptService.create(query, userProfile);
    return ReceiptMetaConverter.toDto(receipt);
  }

}
