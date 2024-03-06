package com.api.milktea.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    private Integer price;
    private Integer quantity;
    private float capital_price;

    @OneToOne(mappedBy = "orderItem",cascade = CascadeType.ALL)
    private Evaluate evaluate;
}
