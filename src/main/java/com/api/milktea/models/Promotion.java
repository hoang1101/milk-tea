package com.api.milktea.models;

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
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;
    private float precent;
    private Date start_day;
    private Date end_day;


}
