package space.shefer.receipt.rest.service.report;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class ReportReceiptFilter {
  @Nullable
  private ReportMetaFilter meta;
  @Nullable
  private ReportItemFilter item;
}
