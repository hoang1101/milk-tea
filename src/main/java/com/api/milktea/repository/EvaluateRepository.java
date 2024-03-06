package com.api.milktea.repository;

import com.api.milktea.models.Evaluate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluateRepository extends CrudRepository<Evaluate,Long> {
    List<Evaluate> findByProductId(long id);
}
