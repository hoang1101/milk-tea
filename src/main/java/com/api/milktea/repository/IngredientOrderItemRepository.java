package com.api.milktea.repository;

import com.api.milktea.models.IngredientOrderItem;
import com.api.milktea.models.IngredientOrderItemId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientOrderItemRepository extends CrudRepository<IngredientOrderItem, IngredientOrderItemId> {
}
