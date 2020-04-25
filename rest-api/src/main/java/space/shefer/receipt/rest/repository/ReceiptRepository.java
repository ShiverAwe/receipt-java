package space.shefer.receipt.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.shefer.receipt.rest.dto.ReportMetaFilter;
import space.shefer.receipt.rest.entity.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>, ReceiptRepositoryCustom {

  @Query("SELECT r FROM Receipt r WHERE status = 'IDLE'")
  List<Receipt> findAllIdle();
}

interface ReceiptRepositoryCustom {

  List<Receipt> getReceipts(ReportMetaFilter filter);

}
