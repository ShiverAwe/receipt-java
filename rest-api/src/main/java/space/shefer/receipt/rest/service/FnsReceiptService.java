package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fns.dto.FnsReceiptDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ItemRepository;
import space.shefer.receipt.rest.repository.ReceiptRepository;

@Service
public class FnsReceiptService {

  @Autowired
  public FnsReceiptService(ReceiptRepository receiptRepository, ItemRepository itemRepository) {
    this.receiptRepository = receiptRepository;
    this.itemRepository = itemRepository;
  }

  private final ReceiptRepository receiptRepository;
  private final ItemRepository itemRepository;

  public void create(FnsReceiptDto capture) {
    Receipt receipt = new Receipt();
    receipt.setFn(capture.getFiscalDriveNumber());
    throw new UnsupportedOperationException("Not implemented yet"); // TODO
  }
}
