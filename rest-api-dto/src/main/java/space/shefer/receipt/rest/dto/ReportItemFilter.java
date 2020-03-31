package space.shefer.receipt.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReportItemFilter {
  @Nullable
  private List<Long> receiptIds;
  @Nullable
  private Double minPrice;
  @Nullable
  private Double maxPrice;
  @Nullable
  private String textEquals;
}
