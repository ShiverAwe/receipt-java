package space.shefer.receipt.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.shefer.receipt.rest.dto.ReportMetaFilter;
import space.shefer.receipt.rest.entity.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>, ReceiptRepositoryCustom {
}

interface ReceiptRepositoryCustom {

  List<Receipt> getReceipts(ReportMetaFilter filter);

}
