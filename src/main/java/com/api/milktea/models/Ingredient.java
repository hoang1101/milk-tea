package com.api.milktea.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "measure_id")
    private Measure measure;

    private Integer quantity;

    private float capital_price;
    @ToString.Exclude
    @OneToMany(mappedBy = "recipeId.ingredient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Recipe> recipe;
    @ToString.Exclude
    @OneToMany(mappedBy = "ingredientOrderItemId.ingredient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<IngredientOrderItem> ingredientorderitem;
}
