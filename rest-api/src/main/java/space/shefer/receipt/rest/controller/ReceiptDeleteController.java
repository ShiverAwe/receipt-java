package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import space.shefer.receipt.rest.service.ReceiptService;

@RestController
public class ReceiptDeleteController {

  private final ReceiptService receiptService;

  @Autowired
  public ReceiptDeleteController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public void delete(long id) {
    receiptService.deleteReceipt(id);
  }
}
