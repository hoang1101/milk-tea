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
@Table(name = "measures")

public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "measure",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Ingredient> ingredients;
}
