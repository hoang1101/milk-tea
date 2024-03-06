package com.api.milktea.controllers;

import com.api.milktea.dtos.IngredientId;
import com.api.milktea.dtos.RecipeRequest;
import com.api.milktea.models.Ingredient;
import com.api.milktea.models.IngredientOrder;
import com.api.milktea.models.Product;
import com.api.milktea.models.Recipe;
import com.api.milktea.services.*;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/ingredient-order")
public class IngredientOrderController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ProductService productService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientOrderService ingredientOrderService;

    @Autowired
    private IngredientOrderItemService ingredientOrderItemService;


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createIngredientOrder(
            @RequestBody Map<?, ?> body
    ) {
        try {
            IngredientOrder createdOrder = ingredientOrderService.createIngredientOrderService(Long.parseLong((String) body.get("staff_id")));
            Long orderId = createdOrder.getId();
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) body.get("data");
            //lap import
            for (Map<String, Object> item : dataList) {
                Integer intValue = (Integer) item.get("id");
                Long longValue = Long.valueOf(intValue);
                // lap ingredient
                for (Ingredient ingredient : ingredientService.getAllIngredientService()) {

                    if (Objects.equals(ingredient.getId(), longValue)) {
                        float capital_price = ingredient.getCapital_price();
                        Integer integer = ingredient.getQuantity();
                        float capital_end = ((capital_price * integer) + ((Integer) item.get("price") * (Integer) item.get("qty"))) / (integer + (Integer) item.get("qty"));
                        Integer quantity_end = integer + (Integer) item.get("qty");
                        ingredientService.updateIngredientQuantityService(longValue, capital_end, quantity_end);
                        ingredientOrderItemService.createIngredientOrderItemService(longValue, orderId, (Integer) item.get("qty"), (Integer) item.get("price"));

                    }
                }
                //tim ra tat ca cac nguyen lieu tuong ung voi nhung nguyen lieu nhap vao
                ArrayList<Recipe> recipe = recipeService.getAllRecipeByIdIngredient(longValue);
                for (Recipe ingredientIdList : recipe) {
                    // tim ra gia tri cua tuong nguyen lieu
                    ArrayList<Recipe> repice_product = (ArrayList<Recipe>) recipeService.getAllRecipeByIdProduct(ingredientIdList.getRecipeId().getProduct().getId());
                    float sum = 0.0F;
                    for (Recipe recipeproduct : repice_product) {
                        Ingredient ingredient1 = ingredientService.getIngredientByIdService(recipeproduct.getRecipeId().getIngredient().getId());
                        sum = sum + ingredient1.getCapital_price() * recipeproduct.getQuantity();
                    }
                    // cap nhat capital_price vao bang product
                    Product product = productService.getProductById(ingredientIdList.getRecipeId().getProduct().getId()).get();
                    product.setCapital_price(sum);
                    productService.CreateProductServiceVer2(product);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("products", "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }
}
