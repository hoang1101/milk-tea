package com.api.milktea.services;

import com.api.milktea.models.Ingredient;
import com.api.milktea.models.Product;
import com.api.milktea.models.Recipe;
import com.api.milktea.models.RecipeId;
import com.api.milktea.repository.IngredientRepository;
import com.api.milktea.repository.ProductsRepository;
import com.api.milktea.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    public Recipe createRecipeService(long product_id, long ingredient_id, int quantity) {
        Recipe recipe = new Recipe();
        RecipeId recipeId = new RecipeId();

        Ingredient ingredient = ingredientRepository.findById(ingredient_id).orElse(null);
        Product product = productsRepository.findById(product_id).orElse(null);

        recipeId.setIngredient(ingredient);
        recipeId.setProduct(product);
        recipe.setRecipeId(recipeId);
        recipe.setQuantity(quantity);
        return recipeRepository.save(recipe);

    }

    public List<Recipe> getAllRecipeByIdProduct (long product_id) {
        return recipeRepository.findByRecipeIdProductId(product_id);
    }

    public ArrayList<Recipe> getAllRecipeByIdIngredient (long ingredient_id) {
        return (ArrayList<Recipe>) recipeRepository.findByRecipeIdIngredientId(ingredient_id);
    }



    public void updatePriceProductService(long longValue) {
        return;
    }
}
