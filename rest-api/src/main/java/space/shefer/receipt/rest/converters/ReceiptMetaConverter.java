package space.shefer.receipt.rest.converters;

import org.apache.commons.lang3.ObjectUtils;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;

public class ReceiptMetaConverter {

  public static ReceiptMetaDto toDto(Receipt receipt) {
    ReceiptMetaDto result = new ReceiptMetaDto();
    result.setId(receipt.getId());
    result.setFn(receipt.getFn());
    result.setFd(receipt.getFd());
    result.setFp(receipt.getFp());
    result.setDate(receipt.getDate());
    result.setSum(receipt.getSum());
    result.setMerchantName(receipt.getMerchantName());
    result.setMerchantInn(receipt.getMerchantInn());
    result.setMerchantPlaceAddress(receipt.getMerchantPlaceAddress());
    result.setPlace(ObjectUtils.firstNonNull(receipt.getMerchantName(), receipt.getMerchantInn()));
    result.setStatus(receipt.getStatus());
    return result;
  }

  public static void map(ReceiptCreateDto receipt, Receipt receiptEntity) {
    receiptEntity.setDate(receipt.getDate());
    receiptEntity.setFn(receipt.getFn());
    receiptEntity.setFd(receipt.getFd());
    receiptEntity.setFp(receipt.getFp());
    receiptEntity.setSum(receipt.getSum());
  }

}
