package space.shefer.receipt.rest.service.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.rest.util.DateUtil;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportMetaFilter {
  @Nullable
  List<Long> ids = null;
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateFrom = null;
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateTo = null;
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
  @Nullable
  private ReportMetaSort sort = null;
  @Nullable
  private Boolean asc = null;
  @Nullable
  private Integer limit = null;
  @Nullable
  private Integer offset = null;
}
