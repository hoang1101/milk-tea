package com.api.milktea.repository;

import com.api.milktea.models.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends CrudRepository<Order,Long> {
}
