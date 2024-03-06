package com.api.milktea.repository;

import com.api.milktea.models.Measure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRespository extends CrudRepository<Measure,Long> {


}
