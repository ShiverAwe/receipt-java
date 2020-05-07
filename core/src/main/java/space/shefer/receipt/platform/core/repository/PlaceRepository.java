package space.shefer.receipt.platform.core.repository;

import org.springframework.data.repository.CrudRepository;
import space.shefer.receipt.platform.core.entity.Place;

public interface PlaceRepository extends CrudRepository<Place, Long> {
}
