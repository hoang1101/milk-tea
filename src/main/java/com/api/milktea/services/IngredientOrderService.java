package com.api.milktea.services;

import com.api.milktea.models.IngredientOrder;
import com.api.milktea.repository.IngredientOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IngredientOrderService {
    @Autowired
    private IngredientOrderRepository ingredientOrderRepository;
    @Autowired
    private UserService userService;


    public IngredientOrder createIngredientOrderService(long staff_id){
        System.out.println(staff_id);
        IngredientOrder ingredientOrder = new IngredientOrder();
        ingredientOrder.setActivate(true);
        ingredientOrder.setDate(new Date());
        ingredientOrder.setUser(userService.getUserById(staff_id));
        return ingredientOrderRepository.save(ingredientOrder);
    }
}
