package com.github.shiverawe.repository;

import com.github.shiverawe.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
