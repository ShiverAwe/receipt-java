package space.shefer.receipt.rest.repository;

import org.springframework.stereotype.Repository;
import space.shefer.receipt.rest.dto.ReportItemFilter;
import space.shefer.receipt.rest.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Item> getItems(ReportItemFilter filter) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Item> cr = cb.createQuery(Item.class);
    Root<Item> root = cr.from(Item.class);
    List<Predicate> predicates = new ArrayList<>();
    if (filter.getReceiptIds() != null) {
      predicates.add(root.get("receipt").get("id").in(filter.getReceiptIds()));
    }
    if (filter.getMinPrice() != null) {
      predicates.add(cb.ge(root.get("price"), filter.getMinPrice()));
    }
    if (filter.getMaxPrice() != null) {
      predicates.add(cb.le(root.get("price"), filter.getMaxPrice()));
    }
    if (filter.getTextEquals() != null) {
      predicates.add(cb.equal(root.get("text"), filter.getTextEquals()));
    }
    cr.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

    TypedQuery<Item> query = entityManager.createQuery(cr);
    return query.getResultList();
  }

}
