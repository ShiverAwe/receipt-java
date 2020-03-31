package space.shefer.receipt.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import space.shefer.receipt.rest.dto.util.DateUtil;

import java.time.LocalDateTime;

@Data
public class ReceiptCreateDto {

  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;

}
