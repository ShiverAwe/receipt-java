package space.shefer.receipt.rest.dto;

import space.shefer.receipt.rest.entity.Receipt;
import lombok.Data;

import java.util.Date;

@Data
public class ReceiptMetaDto {
  private Date date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;
  private String provider;
  private String status;
  private String place;

  public static ReceiptMetaDto of(Receipt receipt) {
    ReceiptMetaDto result = new ReceiptMetaDto();
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
}
