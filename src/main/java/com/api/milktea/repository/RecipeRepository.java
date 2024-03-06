package com.api.milktea.repository;

import com.api.milktea.dtos.IngredientId;
import com.api.milktea.models.Recipe;
import com.api.milktea.models.RecipeId;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RecipeRepository extends CrudRepository<Recipe, RecipeId> {
    List<Recipe> findByRecipeIdProductId(long productId);
    List<Recipe> findByRecipeIdIngredientId(long ingredientId);

}
