package space.shefer.receipt.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.platform.core.dto.ReportItemFilter;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.rest.dto.ReceiptItemDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.service.ItemService;
import space.shefer.receipt.rest.service.ReceiptService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

  private final ReceiptService receiptService;
  private final ItemService itemService;

  @RequestMapping(value = "/receipts", method = RequestMethod.PUT)
  public List<ReceiptMetaDto> receipts(@RequestBody ReportMetaFilter query) {
    return receiptService.getReceipts(query);
  }

  @RequestMapping(value = "/items", method = RequestMethod.PUT)
  public List<ReceiptItemDto> items(@RequestBody ReportItemFilter query) {
    return itemService.getItems(query);
  }

}
