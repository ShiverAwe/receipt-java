package space.shefer.receipt.rest.repository;

import space.shefer.receipt.rest.entity.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {

  List<Place> findAllByInn(String inn);

}
