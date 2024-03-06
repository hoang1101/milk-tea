package com.api.milktea.services;

import com.api.milktea.models.Ingredient;
import com.api.milktea.models.IngredientOrder;
import com.api.milktea.models.IngredientOrderItem;
import com.api.milktea.models.IngredientOrderItemId;
import com.api.milktea.repository.IngredientOrderItemRepository;
import com.api.milktea.repository.IngredientOrderRepository;
import com.api.milktea.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientOrderItemService {

    @Autowired
    private IngredientOrderItemRepository ingredientOrderItemRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientOrderRepository ingredientOrderRepository;

    /**
     * @param id
     * @param order_id
     * @param quantity
     * @param price
     * @return
     */
    public IngredientOrderItem createIngredientOrderItemService(long id,long order_id,int quantity, int price) {
        IngredientOrderItem ingredientOrderItem = new IngredientOrderItem();
        IngredientOrderItemId ingredientOrderItemId = new IngredientOrderItemId();

        IngredientOrder ingredientOrder =  ingredientOrderRepository.findById(order_id).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);

        ingredientOrderItemId.setIngredient(ingredient);
        ingredientOrderItemId.setIngredientOrder(ingredientOrder);
        ingredientOrderItem.setIngredientOrderItemId(ingredientOrderItemId);
        ingredientOrderItem.setQuantity(quantity);
        ingredientOrderItem.setPrice(price);
        return ingredientOrderItemRepository.save(ingredientOrderItem);

    }
}
