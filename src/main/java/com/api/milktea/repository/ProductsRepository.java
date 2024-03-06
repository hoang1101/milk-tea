package com.api.milktea.repository;

import com.api.milktea.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface ProductsRepository extends CrudRepository<Product,Long> {

//    @Query(value = "SELECT u FROM Product u WHERE u.active = true ")
//    List<Product> findAllProduct();

    List<Product> findByActiveTrue();
    List<Product> findByActiveFalse();

    List<Product> findByNameContainingIgnoreCase(String keyword);

//    List<Product> findByProductId(long product_id);
}
