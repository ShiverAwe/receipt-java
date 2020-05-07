package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.ReceiptService;

@RestController
public class ReceiptCreateController {

  private final ReceiptService receiptService;

  @Autowired
  public ReceiptCreateController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ReceiptMetaDto create(@RequestBody ReceiptCreateDto query) {
    Receipt receipt = receiptService.create(query);
    return Receipt.toDto(receipt);
  }

}
