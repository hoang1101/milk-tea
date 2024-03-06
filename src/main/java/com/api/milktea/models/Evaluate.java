package com.api.milktea.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "evaluate")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderitem_id")
    private OrderItem orderItem;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private float star;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private String publicId;
    private String comment;
    private Date date;

}
