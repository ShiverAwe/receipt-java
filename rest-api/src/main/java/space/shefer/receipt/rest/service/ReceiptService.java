package space.shefer.receipt.rest.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.repository.ReceiptRepository;
import space.shefer.receipt.rest.converters.ReceiptMetaConverter;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

  @Value(value = "${receipt.place.default}")
  private String defaultPlace;

  private final ReceiptRepository receiptRepository;
  private final MerchantLogoService merchantLogoService;

  @Autowired
  public ReceiptService(ReceiptRepository receiptRepository, MerchantLogoService merchantLogoService) {
    this.receiptRepository = receiptRepository;
    this.merchantLogoService = merchantLogoService;
  }

  public List<ReceiptMetaDto> getReceipts(ReportMetaFilter metaFilter) {
    List<Receipt> receipts = receiptRepository.getReceipts(metaFilter);
    return receipts.stream().map(ReceiptMetaConverter::toDto)
      .peek(this::setDefaultPlaceIfNull)
      .peek(receipt -> receipt.setMerchantLogoUrl(merchantLogoService.getUrlForImagePlace(receipt.getPlace())))
      .collect(Collectors.toList());
  }

  public Receipt create(ReceiptCreateDto receipt) {
    List<Receipt> matchingReceipts = receiptRepository.getReceipts(
      ReportMetaFilter.builder()
        .fn(receipt.getFn())
        .fd(receipt.getFd())
        .fp(receipt.getFp())
        .dateFrom(receipt.getDate())
        .dateTo(receipt.getDate())
        .sumMax(receipt.getSum())
        .sumMin(receipt.getSum())
        .build()
    );

    if (!matchingReceipts.isEmpty()) {
      return matchingReceipts.get(0);
    }

    Receipt entity = new Receipt();
    ReceiptMetaConverter.map(receipt, entity);
    entity.setStatus(ReceiptStatus.IDLE);
    return receiptRepository.save(entity);
  }

  private void setDefaultPlaceIfNull(ReceiptMetaDto i) {
    if (i.getPlace() == null && !StringUtils.isBlank(defaultPlace)) {
      i.setPlace(defaultPlace);
    }
  }

  public void deleteReceipt(long id) {
    Optional<Receipt> receipt = receiptRepository.findById(id);
    if (receipt.isPresent()) {
      if (receipt.get().getStatus() == ReceiptStatus.LOADED) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt is already loaded");
      }
      receiptRepository.deleteById(id);
    }
  }

}
