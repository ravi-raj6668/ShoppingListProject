package com.test.neokred.shoppinglistproject.repo;

import com.test.neokred.shoppinglistproject.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepo extends JpaRepository<ShoppingItem, Long> {

    List<ShoppingItem> findByCategory(String name);
}
