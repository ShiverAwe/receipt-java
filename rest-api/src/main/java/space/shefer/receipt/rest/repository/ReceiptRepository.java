package space.shefer.receipt.rest.repository;

import space.shefer.receipt.rest.entity.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

  @Query(value = "SELECT receipt FROM Receipt receipt " +
    "WHERE (FALSE=:fnUsed OR receipt.fn=:fn) " +
    "AND (FALSE=:fdUsed OR receipt.fd=:fd) " +
    "AND (FALSE=:fpUsed OR receipt.fp=:fp) " +
    "AND (FALSE=:dateUsed OR receipt.date=:date) " +
    "AND (FALSE=:dateFromUsed OR receipt.date>=:dateFrom) " +
    "AND (FALSE=:dateToUsed OR receipt.date<=:dateTo) " +
    "AND (FALSE=:summaryUsed OR receipt.sum=:summary) " +
    "AND (FALSE=:summaryMinUsed OR receipt.sum>=:summaryMin) " +
    "AND (FALSE=:summaryMaxUsed OR receipt.sum<=:summaryMax) ")
  List<Receipt> findByCredentials(
    @Nullable @Param("fn") String fn, @Param("fnUsed") Boolean fnUsed,
    @Nullable @Param("fd") String fd, @Param("fdUsed") Boolean fdUsed,
    @Nullable @Param("fp") String fp, @Param("fpUsed") Boolean fpUsed,
    @Nullable @Param("date") Date date, @Param("dateUsed") Boolean dateUsed,
    @Nullable @Param("dateFrom") Date dateFrom, @Param("dateFromUsed") Boolean dateFromUsed,
    @Nullable @Param("dateTo") Date dateTo, @Param("dateToUsed") Boolean dateToUsed,
    @Nullable @Param("summary") Double sum, @Param("summaryUsed") Boolean sumUsed,
    @Nullable @Param("summaryMin") Double sumMin, @Param("summaryMinUsed") Boolean sumMinUsed,
    @Nullable @Param("summaryMax") Double sumMax, @Param("summaryMaxUsed") Boolean sumMaxUsed
  );
}
