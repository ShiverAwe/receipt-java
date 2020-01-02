package space.shefer.receipt.rest.repository;

import org.springframework.stereotype.Repository;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Receipt> getReceipts(ReportMetaFilter filter) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Receipt> cr = cb.createQuery(Receipt.class);
    Root<Receipt> root = cr.from(Receipt.class);
    List<Predicate> predicates = new ArrayList<>();
    if (filter.getIds() != null) {
      predicates.add(root.get("id").in(filter.getIds()));
    }
    if (filter.getDateFrom() != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("date"), filter.getDateFrom()));
    }
    if (filter.getDateTo() != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("date"), filter.getDateTo()));
    }
    if (filter.getSumMin() != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("sum"), filter.getSumMin()));
    }
    if (filter.getSumMax() != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("sum"), filter.getSumMax()));
    }
    if (filter.getFd() != null) {
      predicates.add(cb.equal(root.get("fd"), filter.getFd()));
    }
    if (filter.getFn() != null) {
      predicates.add(cb.equal(root.get("fn"), filter.getFn()));
    }
    if (filter.getFp() != null) {
      predicates.add(cb.equal(root.get("fp"), filter.getFp()));
    }
    if (filter.getPlace() != null) {
      predicates.add(cb.equal(root.get("place").get("text"), filter.getPlace()));
    }

    cr
      .select(root)
      .where(cb.and(predicates.toArray(new Predicate[0])));

    if (filter.getSort() != null){
      Path<Object> orderByExpression = root.get(filter.getSort().getReceiptFieldName());

      Order order = ((filter.getAsc() == null) || filter.getAsc())
        ? cb.asc(orderByExpression)
        : cb.desc(orderByExpression);

      cr.orderBy(order);
    }

    TypedQuery<Receipt> query = entityManager.createQuery(cr);
    if (filter.getLimit() != null) {
      query.setMaxResults(filter.getLimit());
    }
    if (filter.getOffset() != null){
      query.setFirstResult(filter.getOffset());
    }

    return query.getResultList();
  }

}
