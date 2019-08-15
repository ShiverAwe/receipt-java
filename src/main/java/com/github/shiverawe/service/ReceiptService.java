package com.github.shiverawe.service;

import com.github.shiverawe.dto.ReceiptDto;
import com.github.shiverawe.entity.Receipt;
import com.github.shiverawe.repository.ReceiptRepository;
import com.github.shiverawe.service.report.ReportMetaFilter;
import com.github.shiverawe.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

  private final ReceiptRepository repository;

  @Autowired
  public ReceiptService(ReceiptRepository repository) {
    this.repository = repository;
  }

  public List<ReceiptDto> report(ReportMetaFilter metaFilter) {
    List<Receipt> receipts = repository.findByCredentials(
      metaFilter.getFn(), metaFilter.getFn() != null,
      metaFilter.getFd(), metaFilter.getFd() != null,
      metaFilter.getFp(), metaFilter.getFp() != null,
      metaFilter.getDateEquals(), metaFilter.getDateEquals() != null,
      metaFilter.getSumEquals(), metaFilter.getSumEquals() != null);
    return receipts.stream().map(ReceiptDto::of).collect(Collectors.toList());
  }

}
