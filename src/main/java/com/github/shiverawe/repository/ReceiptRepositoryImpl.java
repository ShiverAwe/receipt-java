package com.github.shiverawe.repository;

import com.github.shiverawe.ReceiptFilter;
import com.github.shiverawe.entity.Receipt;
import lombok.var;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepositoryCustom {

  @PersistenceContext
  private EntityManager myEntityManager;

  @Override
  public List<Receipt> findByFilter(ReceiptFilter filter) {
    var builder = myEntityManager.getCriteriaBuilder();
    var query = builder.createQuery(Receipt.class);
    Root<Receipt> root = query.from(Receipt.class);
    query.select(builder.construct(Receipt.class, prepareSelectClause(builder, root)))
      .where(prepareWhereClause(filter, builder, root));
    return myEntityManager.createQuery(query).getResultList();
  }

  private static Predicate prepareWhereClause(ReceiptFilter parameters, CriteriaBuilder cb,
                                              Root<Receipt> root) {
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb.greaterThanOrEqualTo(root.get("date"), parameters.getDateFrom()));
    predicates.add(cb.lessThanOrEqualTo(root.get("date"), parameters.getDateTo()));
    predicates.add(cb.greaterThanOrEqualTo(root.get("sum"), parameters.getSumMin()));
    predicates.add(cb.lessThanOrEqualTo(root.get("sum"), parameters.getDateTo()));
    return cb.and(predicates.toArray(new Predicate[0]));
  }

  private static Selection<?>[] prepareSelectClause(CriteriaBuilder cb, Root<Receipt> root) {
    return new Selection[]{
      cb.coalesce(root.get("id"), 0),
      cb.coalesce(root.get("date"), 0),
      cb.coalesce(root.get("fn"), 0),
      cb.coalesce(root.get("fd"), 0),
      cb.coalesce(root.get("fp"), 0),
      cb.coalesce(root.get("sum"), 0),
    };
  }
}
