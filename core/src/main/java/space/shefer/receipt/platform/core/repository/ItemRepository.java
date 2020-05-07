package space.shefer.receipt.platform.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.shefer.receipt.platform.core.dto.ReportItemFilter;
import space.shefer.receipt.platform.core.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

}

interface ItemRepositoryCustom {
    List<Item> getItems(ReportItemFilter filter);
}
