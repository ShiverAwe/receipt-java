package space.shefer.receipt.rest.dto;

import lombok.Data;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.report.ReceiptStatus;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Data
public class ReceiptMetaDto {
  @Nullable
  private Long id;
  private LocalDateTime date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;
  private String provider;
  private ReceiptStatus status;
  private String place;

  public static ReceiptMetaDto of(Receipt receipt) {
    ReceiptMetaDto result = new ReceiptMetaDto();
    result.setId(receipt.getId());
    result.setFn(receipt.getFn());
    result.setFd(receipt.getFd());
    result.setFp(receipt.getFp());
    result.setDate(receipt.getDate());
    result.setSum(receipt.getSum());
    result.setProvider(receipt.getProvider());
    if (receipt.getPlace() != null) {
      result.setPlace(receipt.getPlace().getText());
    }
    result.setStatus(receipt.getStatus());
    return result;
  }

  public Receipt setTo(Receipt receipt){
    receipt.setDate(getDate());
    receipt.setFn(getFn());
    receipt.setFd(getFd());
    receipt.setFp(getFp());
    receipt.setSum(getSum());
    receipt.setStatus(getStatus());
    receipt.setProvider(getProvider());
    // TODO add place
    return receipt;
  }
}
