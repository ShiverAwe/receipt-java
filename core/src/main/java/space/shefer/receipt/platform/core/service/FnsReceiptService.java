package space.shefer.receipt.platform.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shefer.receipt.fnssdk.dto.FnsItemDto;
import space.shefer.receipt.fnssdk.dto.FnsReceiptDto;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.entity.Item;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.repository.ItemRepository;
import space.shefer.receipt.platform.core.repository.ReceiptRepository;

import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FnsReceiptService {

  private final ReceiptRepository receiptRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Receipt update(FnsReceiptDto receiptDto, Receipt receipt, String provider) {
    receipt.setFn(receiptDto.getFiscalDriveNumber());
    receipt.setFd(String.valueOf(receiptDto.getFiscalDocumentNumber()));
    receipt.setFp(String.valueOf(receiptDto.getFiscalSign()));
    receipt.setSum(receiptDto.getTotalSum() / 100d);
    receipt.setDate(receiptDto.getDateTime());
    receipt.setMerchantName(receiptDto.getUser());
    receipt.setMerchantInn(receiptDto.getUserInn());
    receipt.setMerchantPlaceAddress(receiptDto.getRetailPlaceAddress());
    receipt.setStatus(ReceiptStatus.LOADED);
    receipt.setProvider(provider);

    List<Receipt> matchingReceipts = receiptRepository.getReceipts(
      ReportMetaFilter.builder()
        .fn(receipt.getFn())
        .fd(receipt.getFd())
        .fp(receipt.getFp())
        .dateFrom(receipt.getDate())
        .dateTo(receipt.getDate())
        .sumMax(receipt.getSum())
        .sumMin(receipt.getSum())
        .statuses(EnumSet.of(receipt.getStatus()))
        .build()
    );

    if (!matchingReceipts.isEmpty()) {
      return matchingReceipts.get(0);
    }

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
