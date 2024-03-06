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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    private String address;

    @Column(name = "status", columnDefinition="tinyint(1) default null")
    private Boolean status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<OrderItem> orderItems;
}
