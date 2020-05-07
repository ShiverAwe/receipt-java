package space.shefer.receipt.platform.jobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.repository.ReceiptRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptService {

  private final ReceiptRepository receiptRepository;

  public List<Receipt> getAllIdle() {
    return receiptRepository.findAllIdle();
  }

  public Receipt setStatus(Receipt receipt, ReceiptStatus status) {
    receipt.setStatus(status);
    return receiptRepository.save(receipt);
  }

}
