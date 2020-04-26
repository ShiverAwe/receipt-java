package space.shefer.receipt.rest.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.shefer.receipt.fnssdk.dto.FnsReceiptDto;
import space.shefer.receipt.fnssdk.service.FnsService;
import space.shefer.receipt.rest.dto.ReceiptProvider;
import space.shefer.receipt.rest.dto.ReceiptStatus;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.FnsReceiptService;
import space.shefer.receipt.rest.service.ReceiptService;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class ReceiptLoadJob {

  private final ReceiptService receiptService;
  private final FnsService fnsService;
  private final FnsReceiptService fnsReceiptService;

  @Autowired
  public ReceiptLoadJob(ReceiptService receiptService, FnsService fnsService, FnsReceiptService fnsReceiptService) {
    this.receiptService = receiptService;
    this.fnsService = fnsService;
    this.fnsReceiptService = fnsReceiptService;
  }

  @Scheduled(initialDelay = 10000)
  public void load() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss");
    List<Receipt> receipts = receiptService.getAllIdle();

    receipts.forEach(receipt -> {
        String rawReceipt = fnsService.getReceiptExists(
          receipt.getFn(),
          receipt.getFd(),
          receipt.getFp(),
          receipt.getDate().format(dateTimeFormatter),
          receipt.getSum().floatValue()
        );

        if (rawReceipt != null) {
          fnsReceiptService.create(FnsReceiptDto.fromString(rawReceipt), receipt, ReceiptProvider.NALOG.name());
        }
        else {
          receiptService.setStatus(receipt, ReceiptStatus.FAILED);
        }
      }
    );
  }

}
