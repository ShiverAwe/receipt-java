package space.shefer.receipt.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.platform.core.dto.util.DateUtil;

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
  List<String> ids;

  @Schema(
    description = DateUtil.RECEIPT_DATETIME_DESCRIPTION,
    pattern = DateUtil.RECEIPT_DATETIME_REGEX,
    example = "20170131T2359"
  )
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateFrom;

  @Schema(
    description = DateUtil.RECEIPT_DATETIME_DESCRIPTION,
    pattern = DateUtil.RECEIPT_DATETIME_REGEX,
    example = "20170131T2359"
  )
  @Nullable
  @JsonFormat(pattern = DateUtil.RECEIPT_DATETIME_PATTERN)
  private LocalDateTime dateTo;

  @Schema(
    minimum = "0"
  )
  @Nullable
  private Double sumMin;

  @Schema(
    minimum = "0"
  )
  @Nullable
  private Double sumMax;

  @Schema(
    description = "ФН, Фискальный Накопитель",
    format = "numeric",
    minLength = 16,
    maxLength = 16,
    example = "9251440300048237"
  )
  @Nullable
  private String fn;

  @Schema(
    description = "ФД, Фискальный Документ",
    format = "numeric",
    minLength = 5
  )
  @Nullable
  private String fd;

  @Schema(
    description = "ФП, Фискальный Признак",
    format = "numeric",
    minLength = 5
  )
  @Nullable
  private String fp;

  @Schema(
    description = "Filters for exact match of merchant name",
    deprecated = true
  )
  @Nullable
  private String place;

  @Nullable
  private ReportMetaSort sort;

  @Schema(defaultValue = "true")
  @Nullable
  private Boolean asc;

  @Schema(
    example = "13",
    minimum = "0"
  )
  @Nullable
  private Integer limit;

  @Schema(
    defaultValue = "0",
    example = "13",
    minimum = "0"
  )
  @Nullable
  private Integer offset;

  @Nullable
  private EnumSet<ReceiptStatus> statuses;

}
