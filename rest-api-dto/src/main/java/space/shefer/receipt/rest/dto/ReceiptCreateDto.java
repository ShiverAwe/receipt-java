package space.shefer.receipt.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import space.shefer.receipt.platform.core.dto.util.DateUtil;

import java.time.LocalDateTime;
import javax.validation.constraints.Pattern;

@Data
public class ReceiptCreateDto {
  public static final String POSITIVE_16_DIGIT_NUMBER = "^(?!0*$)[0-9]{16}$";
  public static final String POSITIVE_NUMBER_REGEX = "^(?!0*$)[0-9]+$";

  @Schema(
    description = DateUtil.RECEIPT_DATETIME_DESCRIPTION,
    pattern = DateUtil.RECEIPT_DATETIME_REGEX,
    example = "20170131T2359"
  )
  @JsonFormat(
    pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime date;
  @Pattern(regexp = POSITIVE_16_DIGIT_NUMBER,
            message = "The fiscal number (fn) must a positive number and have a length of 16 characters.")
  private String fn;
  @Pattern(regexp = POSITIVE_NUMBER_REGEX,
            message = "The fiscal document (fd) must be a positive number.")
  private String fd;
  @Pattern(regexp = POSITIVE_NUMBER_REGEX,
          message = "The fiscal indication (fp) must be a positive number.")
  private String fp;
  private Double sum;

}
