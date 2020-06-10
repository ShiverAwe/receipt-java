package space.shefer.receipt.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import space.shefer.receipt.platform.core.dto.util.DateUtil;

import java.time.LocalDateTime;
import javax.validation.constraints.Pattern;

@Data
public class ReceiptCreateDto {

  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime date;
  @Pattern(regexp = "^(?!0*$)[0-9]{16}$",
            message = "The fiscal number (fn) must a positive number and have a length of 16 characters.")
  private String fn;
  @Pattern(regexp = "^(?!0*$)[0-9]+$", message = "The fiscal document (fd) must be a positive number.")
  private String fd;
  @Pattern(regexp = "^(?!0*$)[0-9]+$", message = "The fiscal indication (fp) must be a positive number.")
  private String fp;
  private Double sum;

}
