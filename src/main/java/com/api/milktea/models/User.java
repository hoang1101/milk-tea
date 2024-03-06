package com.api.milktea.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor // tạo contructor ây đủ tất cả thuộc tính
@NoArgsConstructor // tạo 1 con trotructor không có tham so
@Getter
@Setter
@Builder
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;
    private String password;
    @Email
    private String email;
    @Column(unique = true, length = 30)
    private String username;
    @Column(unique = true, length = 10)

    private String phone;
    private String address;
    @Column(columnDefinition = "Boolean default false") // true nam - false nu - null khac
    private Boolean gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @Column(columnDefinition = "Boolean default false")// true la dang hoat dong - false bi khoa
    private Boolean isActive;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private String publicId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Order> orders;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Promotion> promotions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<IngredientOrder> ingredientOrders;


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Evaluate> evaluate;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Order> order;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", isActive=" + isActive +
                ", role=" + role +
                ", orders=" + orders +
                ", promotions=" + promotions +
                ", ingredientOrders=" + ingredientOrders +
                ", evaluate=" + evaluate +
                ", order=" + order +
                '}';
    }

}
