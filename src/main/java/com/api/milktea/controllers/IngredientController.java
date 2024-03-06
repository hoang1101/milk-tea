package com.api.milktea.controllers;

import com.api.milktea.models.Ingredient;
import com.api.milktea.services.IngredientService;
import com.api.milktea.services.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ingredient")
public class IngredientController {


    @Autowired
    private IngredientService ingredientService;


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createIngredient(
            @RequestBody Map<String, String> body
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(ingredientService.createIngredientService(body.get("name"), Long.parseLong(body.get("measure_id"))), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateIngredient(
            @PathVariable long id,
            @RequestBody Map<String, String> body
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(ingredientService.updateIngredientService(id, body.get("name"), Long.parseLong(body.get("measure_id"))), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllIngredient(

    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(ingredientService.getAllIngredientService(), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseObject> getAllIngredient(
            @PathVariable long id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(ingredientService.getIngredientByIdService(id), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> delteteIngredient(
            @PathVariable long id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(ingredientService.deleteIngredientService(id), "Delete nguyen lieu thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }
}
