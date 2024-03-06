package com.api.milktea.controllers;

import com.api.milktea.dtos.RecipeRequest;
import com.api.milktea.models.RecipeId;
import com.api.milktea.repository.RecipeRepository;
import com.api.milktea.services.RecipeService;
import com.api.milktea.services.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createRecipe(
            @RequestBody RecipeRequest[] data
    ) {
        try {
            for(RecipeRequest item : data) {
                recipeService.createRecipeService(2, item.getIngredient_id(), item.getQuantity());

            }
            return ResponseEntity.status(HttpStatus.OK).body(

                    new ResponseObject( recipeService.getAllRecipeByIdProduct(2),"Lấy danh sản phẩm thành công", "true")
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-recipe/{product_id}")
    public ResponseEntity<ResponseObject> getRecipeById(@PathVariable long product_id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(

                    new ResponseObject( recipeService.getAllRecipeByIdProduct(product_id),"Lấy danh sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


}
