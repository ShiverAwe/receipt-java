package space.shefer.receipt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptCreateNewFormatDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.ReceiptService;

@RestController
public class ReceiptCreateResponseDto {

  private final ReceiptService receiptService;

  @Autowired
  public ReceiptCreateResponseDto(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ReceiptCreateNewFormatDto create(@RequestBody ReceiptCreateDto query) {
    ReceiptCreateNewFormatDto receiptCreateNewFormatDto = new ReceiptCreateNewFormatDto();
    Receipt receipt = receiptService.create(query);
    receiptCreateNewFormatDto.setId(receipt.getId());
    receiptCreateNewFormatDto.setStatus(receipt.getStatus());
    return receiptCreateNewFormatDto;
  }

}
