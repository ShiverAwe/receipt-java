package space.shefer.receipt.platform.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.shefer.receipt.fnssdk.dto.FnsResponseDto;
import space.shefer.receipt.fnssdk.excepion.AuthorizationFailedException;
import space.shefer.receipt.fnssdk.excepion.ReceiptNotFoundException;
import space.shefer.receipt.fnssdk.service.FnsService;
import space.shefer.receipt.platform.core.dto.ReceiptProvider;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.service.FnsReceiptService;
import space.shefer.receipt.platform.jobs.service.ReceiptService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReceiptLoadJob {

  private final ReceiptService receiptService;
  private final FnsService fnsService;
  private final FnsReceiptService fnsReceiptService;

  @Scheduled(fixedDelay = 10000)
  public void load() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    List<Receipt> receipts = receiptService.getAllIdle();

    receipts.forEach(receipt -> {
        try {
          String rawReceipt = fnsService.getReceiptExists(
            receipt.getFn(),
            receipt.getFd(),
            receipt.getFp(),
            receipt.getDate().format(dateTimeFormatter),
            receipt.getSum().floatValue()
          );

          if (rawReceipt != null) {
            fnsReceiptService.update(
              FnsResponseDto.fromString(rawReceipt).document.receipt,
              receipt,
              ReceiptProvider.NALOG.name()
            );
          }
          else {
            receiptService.setStatus(receipt, ReceiptStatus.FAILED);
          }
        }
        catch (AuthorizationFailedException e){
          e.printStackTrace();
        }
        catch (ReceiptNotFoundException e) {
          receiptService.setStatus(receipt, ReceiptStatus.FAILED);
          e.printStackTrace();
        }
        catch (Exception e) {
          receiptService.setStatus(receipt, ReceiptStatus.IDLE);
          e.printStackTrace();
        }
      }
    );
  }

}
