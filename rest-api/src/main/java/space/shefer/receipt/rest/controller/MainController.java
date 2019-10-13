package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import space.shefer.receipt.rest.dto.ReceiptItemDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.service.ItemService;
import space.shefer.receipt.rest.service.ReceiptService;
import space.shefer.receipt.rest.service.report.ReportItemFilter;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;

import java.util.List;

@RestController
public class MainController {

  private final ReceiptService receiptService;
  private final ItemService itemService;

  @Autowired
  public MainController(ReceiptService receiptService, ItemService itemService) {
    this.receiptService = receiptService;
    this.itemService = itemService;
  }

  @RequestMapping(value = "/receipts", method = RequestMethod.PUT)
  public List<ReceiptMetaDto> receipts(@RequestBody ReportMetaFilter query) {
    return receiptService.getReceipts(query);
  }

  @RequestMapping(value = "/items", method = RequestMethod.PUT)
  public List<ReceiptItemDto> items(@RequestBody ReportItemFilter query) {
    return itemService.getItems(query);
  }

  @RequestMapping(value = "/merchant", method = RequestMethod.GET)
  public String merchants(int inn) {
    throw HttpServerErrorException.create(HttpStatus.NOT_IMPLEMENTED, "Not implemented", HttpHeaders.EMPTY, null, null);
  }

  @RequestMapping(value = "/ping")
  public String ping() {
    return "pong";
  }

}
