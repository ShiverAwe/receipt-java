package space.shefer.receipt.rest.dto;

import lombok.Getter;

public enum ReportMetaSort {
  DATE("date");

  ReportMetaSort(String receiptFieldName){
    this.receiptFieldName = receiptFieldName;
  }

  @Getter
  private final String receiptFieldName;

}
