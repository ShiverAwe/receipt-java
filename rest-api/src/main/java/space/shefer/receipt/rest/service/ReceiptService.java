package space.shefer.receipt.rest.service;

import space.shefer.receipt.rest.dto.ReceiptDto;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ReceiptRepository;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.rest.service.report.ReportReceiptFilter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

  private final ReceiptRepository repository;

  @Autowired
  public ReceiptService(ReceiptRepository repository) {
    this.repository = repository;
  }

  public List<ReceiptDto> report(ReportReceiptFilter filter) {
    ReportMetaFilter metaFilter = filter.getMeta() != null ? filter.getMeta() : new ReportMetaFilter();
    List<Receipt> receipts = repository.findByCredentials(
      metaFilter.getFn(), metaFilter.getFn() != null,
      metaFilter.getFd(), metaFilter.getFd() != null,
      metaFilter.getFp(), metaFilter.getFp() != null,
      metaFilter.getDateEquals(), metaFilter.getDateEquals() != null,
      metaFilter.getSumEquals(), metaFilter.getSumEquals() != null);
    return receipts.stream().map(ReceiptDto::of).collect(Collectors.toList());
  }

}
