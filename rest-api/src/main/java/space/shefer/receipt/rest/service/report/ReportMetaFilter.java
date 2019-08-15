package space.shefer.receipt.rest.service.report;

import space.shefer.receipt.rest.util.DateUtil;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.Date;

@Data
public class ReportMetaFilter {
  @Nullable
  private String dateEquals;
  @Nullable
  private String dateFrom;
  @Nullable
  private String dateTo;
  @Nullable
  private Double sumEquals;
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
  public Date getDateEquals() {
    return DateUtil.parseReceiptDate(dateEquals);
  }

  @Nullable
  public Date getDateFrom() {
    return DateUtil.parseReceiptDate(dateFrom);
  }

  @Nullable
  public Date getDateTo() {
    return DateUtil.parseReceiptDate(dateTo);
  }

}
