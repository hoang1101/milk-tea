package com.api.milktea.services;

import com.api.milktea.models.Ingredient;
import com.api.milktea.repository.IngredientRepository;
import com.api.milktea.repository.MeasureRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MeasureService measureService;

    public Ingredient createIngredientService(String name, long measure_id){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setMeasure(measureService.findMeasureByID(measure_id));
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredientService(long id,String name, long measure_id){

        Ingredient ingredient =  ingredientRepository.findById(id).get();
        ingredient.setName(name);
        ingredient.setMeasure(measureService.findMeasureByID(measure_id));
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredientService() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    public Ingredient getIngredientByIdService(long id) {
        return  ingredientRepository.findById(id).orElse(null);
    }

    public Boolean deleteIngredientService(long id) {
        try {
            ingredientRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Ingredient updateIngredientQuantityService(long id, float capital , int quantity){

        Ingredient ingredient =  ingredientRepository.findById(id).get();
        ingredient.setCapital_price(capital);
        ingredient.setQuantity(quantity);
        return ingredientRepository.save(ingredient);
    }
}
