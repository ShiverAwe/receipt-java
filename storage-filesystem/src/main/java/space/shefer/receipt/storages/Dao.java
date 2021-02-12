package space.shefer.receipt.storages;

import javax.annotation.Nullable;
import java.util.List;

public interface Dao<T> {

  T save(String id, T entity);

  void deleteAll();

  T getById(String id);
  @Nullable
  T getByIdOrNull(String id);
  List<T> getAll();
  void delete(String id);

}
