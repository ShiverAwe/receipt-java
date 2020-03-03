package space.shefer.receipt.rest.dto;

import lombok.Data;

@Data
public class TgbotCreateBody {
  private String userId;
  private String receiptJson;
}
