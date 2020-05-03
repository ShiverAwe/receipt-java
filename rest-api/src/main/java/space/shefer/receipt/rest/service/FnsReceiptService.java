package space.shefer.receipt.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import space.shefer.receipt.fnssdk.dto.FnsItemDto;
import space.shefer.receipt.fnssdk.dto.FnsReceiptDto;
import space.shefer.receipt.rest.dto.ReceiptStatus;
import space.shefer.receipt.rest.dto.ReportMetaFilter;
import space.shefer.receipt.rest.entity.Item;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ItemRepository;
import space.shefer.receipt.rest.repository.ReceiptRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FnsReceiptService {

  private static ObjectMapper objectMapper = new ObjectMapper();

  private final ReceiptRepository receiptRepository;
  private final ItemRepository itemRepository;

  @SneakyThrows
  public Receipt create(FnsReceiptDto receiptDto) {
    Receipt receipt = new Receipt();
    receipt.setFn(receiptDto.getFiscalDriveNumber());
    receipt.setFd(String.valueOf(receiptDto.getFiscalDocumentNumber()));
    receipt.setFp(String.valueOf(receiptDto.getFiscalSign()));
    receipt.setSum(receiptDto.getTotalSum() / 100d);
    receipt.setDate(LocalDateTime.ofEpochSecond(receiptDto.getDateTime(), 0, ZoneOffset.UTC));
    receipt.setStatus(ReceiptStatus.LOADED);
    receipt.setRawData(objectMapper.writeValueAsString(receiptDto));
    receipt.setProvider("TGBOT_NALOG");

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
