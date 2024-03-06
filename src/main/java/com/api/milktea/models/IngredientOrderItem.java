package com.api.milktea.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "ingredientorderitem")
public class IngredientOrderItem {
    @Autowired
    @EmbeddedId
    private IngredientOrderItemId ingredientOrderItemId;
    private Integer quantity;
    private Integer price;
}
