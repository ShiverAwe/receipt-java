package space.shefer.receipt.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.rest.dto.util.DateUtil;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportMetaFilter {
  @Nullable
  List<Long> ids;
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateFrom;
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateTo;
  @Nullable
  private Double sumMin;
  @Nullable
  private Double sumMax;
  @Nullable
  private String fn;
  @Nullable
  private String fd;
  @Nullable
  private String fp;
  @Nullable
  private String place;
  @Nullable
  private ReportMetaSort sort;
  @Nullable
  private Boolean asc;
  @Nullable
  private Integer limit;
  @Nullable
  private Integer offset;

  @Nullable
  private EnumSet<ReceiptStatus> statuses;
}
