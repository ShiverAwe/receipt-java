package space.shefer.receipt.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.util.DateUtil;

import java.time.LocalDateTime;

@Data
public class ReceiptCreateDto {

  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;

  public Receipt setTo(Receipt receipt){
    receipt.setDate(getDate());
    receipt.setFn(getFn());
    receipt.setFd(getFd());
    receipt.setFp(getFp());
    receipt.setSum(getSum());
    return receipt;
  }
}