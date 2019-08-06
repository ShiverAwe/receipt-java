package com.github.shiverawe.repository;

import com.github.shiverawe.ReceiptFilter;
import com.github.shiverawe.entity.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long>, ReceiptRepositoryCustom {

  @Query(value = "SELECT * FROM receipt " +
    "WHERE (:fn IS NULL OR fd=:fn) " +
    "AND (:fd IS NULL OR fd=:fd) " +
    "AND (:fp IS NULL OR fp=:fp) " +
    "AND (:date IS NULL OR date=:date) " +
    "AND (:sum IS NULL OR sum=:sum)", nativeQuery = true)
  Iterable<Receipt> findByCredentials(
    @Nullable @Param("fn") String fn,
    @Nullable @Param("fd") String fd,
    @Nullable @Param("fp") String fp,
    @Nullable @Param("date") Date date,
    @Nullable @Param("sum") String sum);

}

interface ReceiptRepositoryCustom {
  List<Receipt> findByFilter(ReceiptFilter filter);
}