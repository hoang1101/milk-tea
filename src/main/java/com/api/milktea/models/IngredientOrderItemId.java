package com.api.milktea.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Embeddable
@Data
public class IngredientOrderItemId implements Serializable {
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ingredient_order_id")
    private IngredientOrder ingredientOrder;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
