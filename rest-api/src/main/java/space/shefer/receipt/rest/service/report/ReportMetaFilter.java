package space.shefer.receipt.rest.service.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.rest.util.DateUtil;

import javax.annotation.Nullable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportMetaFilter {
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private Date dateFrom = null;
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private Date dateTo = null;
  @Nullable
  private Double sumMin = null;
  @Nullable
  private Double sumMax = null;
  @Nullable
  private String fn = null;
  @Nullable
  private String fd = null;
  @Nullable
  private String fp = null;
  @Nullable
  private String place = null;
}
