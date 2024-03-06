package com.api.milktea.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "ingredientorder")
public class IngredientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(columnDefinition = "tinyint(1) default null")
    private boolean activate;
    @ToString.Exclude
    @OneToMany(mappedBy = "ingredientOrderItemId.ingredientOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<IngredientOrderItem> ingredientorderitem;
}
