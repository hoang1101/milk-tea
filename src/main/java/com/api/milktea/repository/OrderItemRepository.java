package com.api.milktea.repository;

import com.api.milktea.models.OrderItem;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
