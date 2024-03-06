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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
    private int price;
    private String description;
    private Boolean active;
    private float capital_price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private String publicId;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<OrderItem> orderItems;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Evaluate> evaluate;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Promotion> promotion;

    @OneToMany(mappedBy = "recipeId.product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Recipe> recipe;
}
