package space.shefer.receipt.rest.repository;

import space.shefer.receipt.rest.entity.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

  @Query(value = "SELECT r FROM Receipt r " +
    "WHERE (NOT TRUE=:fnUsed OR r.fn=:fn) " +
    "AND (NOT TRUE=:fdUsed OR r.fd=:fd) " +
    "AND (NOT TRUE=:fpUsed OR r.fp=:fp) " +
    "AND (NOT TRUE=:dateUsed OR r.date=:date) " +
    "AND (NOT TRUE=:summaryUsed OR r.sum=:summary)")
  List<Receipt> findByCredentials(
    @Nullable @Param("fn") String fn, @Param("fnUsed") Boolean fnUsed,
    @Nullable @Param("fd") String fd, @Param("fdUsed") Boolean fdUsed,
    @Nullable @Param("fp") String fp, @Param("fpUsed") Boolean fpUsed,
    @Nullable @Param("date") Date date, @Param("dateUsed") Boolean dateUsed,
    @Nullable @Param("summary") Double sum, @Param("summaryUsed") Boolean sumUsed);

}