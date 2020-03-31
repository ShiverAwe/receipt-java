package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fns.dto.FnsItemDto;
import space.shefer.receipt.fns.dto.FnsReceiptDto;
import space.shefer.receipt.rest.dto.ReceiptStatus;
import space.shefer.receipt.rest.entity.Item;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ItemRepository;
import space.shefer.receipt.rest.repository.ReceiptRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class FnsReceiptService {

  @Autowired
  public FnsReceiptService(ReceiptRepository receiptRepository, ItemRepository itemRepository) {
    this.receiptRepository = receiptRepository;
    this.itemRepository = itemRepository;
  }

  private final ReceiptRepository receiptRepository;
  private final ItemRepository itemRepository;

  public Receipt create(FnsReceiptDto receiptDto) {
    Receipt receipt = new Receipt();
    receipt.setFn(receiptDto.getFiscalDriveNumber());
    receipt.setFd(String.valueOf(receiptDto.getFiscalDocumentNumber()));
    receipt.setFp(String.valueOf(receiptDto.getFiscalSign()));
    receipt.setSum(receiptDto.getTotalSum() / 100d);
    receipt.setDate(LocalDateTime.ofEpochSecond(receiptDto.getDateTime(), 0, ZoneOffset.UTC));
    receipt.setStatus(ReceiptStatus.LOADED);
    receipt.setProvider("TGBOT_NALOG");
    Receipt savedReceipt = receiptRepository.save(receipt);

    for (FnsItemDto itemDto : receiptDto.getItems()) {
      Item item = new Item();
      item.setPrice(itemDto.getPrice() / 100d);
      item.setAmount(itemDto.getQuantity());
      item.setText(itemDto.getName());
      item.setReceipt(savedReceipt);
      Item savedItem = itemRepository.save(item);
      savedReceipt.getItems().add(savedItem);
    }

    return savedReceipt;
  }

}
