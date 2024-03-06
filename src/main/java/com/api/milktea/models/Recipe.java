package com.api.milktea.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "recipe")

public class Recipe {

    @Autowired
    @EmbeddedId
    private RecipeId recipeId;

    private int quantity;


    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", quantity=" + quantity +
                '}';
    }
}
