package com.github.shiverawe;

import lombok.Data;

import javax.annotation.Nullable;
import java.util.Date;

@Data
public class ReceiptFilter {
  @Nullable
  private Date dateFrom;
  @Nullable
  private Date dateTo;
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
}
