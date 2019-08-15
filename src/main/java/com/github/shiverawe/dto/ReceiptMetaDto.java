package com.github.shiverawe.dto;

import com.github.shiverawe.entity.Receipt;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ReceiptMetaDto {
  private Date date;
  private String fn;
  private String fd;
  private String fp;
  private Double sum;
  private String provider;
  private String status;
  private String place;

  public static ReceiptMetaDto of(Receipt receipt){
    ReceiptMetaDto result = new ReceiptMetaDto();
    result.setFn(receipt.getFn());
    result.setFd(receipt.getFd());
    result.setFp(receipt.getFp());
    result.setDate(receipt.getDate());
    result.setSum(receipt.getSum());
    result.setProvider(receipt.getProvider());
    result.setPlace(receipt.getPlace().getText());
    result.setStatus(receipt.getStatus());
    return result;
  }
}
