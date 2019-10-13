package space.shefer.receipt.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.shefer.receipt.rest.entity.Item;
import space.shefer.receipt.rest.service.report.ReportItemFilter;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

}

interface ItemRepositoryCustom {
    List<Item> getItems(ReportItemFilter filter);
}
