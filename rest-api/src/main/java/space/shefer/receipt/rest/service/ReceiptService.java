package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ReceiptRepository;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

  private final ReceiptRepository receiptRepository;

  @Autowired
  public ReceiptService(ReceiptRepository receiptRepository) {
    this.receiptRepository = receiptRepository;
  }

  public List<ReceiptMetaDto> getReceipts(ReportMetaFilter metaFilter) {
    List<Receipt> receipts = receiptRepository.getReceipts(metaFilter);
    return receipts.stream().map(ReceiptMetaDto::of).collect(Collectors.toList());
  }

  public Long create(ReceiptMetaDto receipt) {
    if (receipt.getId() != null) {
      throw new IllegalArgumentException("Id should be null");
    }
    Receipt savedReceipt = receiptRepository.save(receipt.setTo(new Receipt()));
    return savedReceipt.getId();
  }

}
