package com.api.milktea.repository;

import com.api.milktea.models.IngredientOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientOrderRepository extends CrudRepository<IngredientOrder,Long> {
}
