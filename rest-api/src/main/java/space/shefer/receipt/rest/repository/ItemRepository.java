package space.shefer.receipt.rest.repository;

import space.shefer.receipt.rest.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
