package space.shefer.receipt.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>, ReceiptRepositoryCustom {
}

interface ReceiptRepositoryCustom {

  List<Receipt> getReceipts(ReportMetaFilter filter);

}
