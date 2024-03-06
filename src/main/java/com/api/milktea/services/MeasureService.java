package com.api.milktea.services;

import com.api.milktea.models.Measure;
import com.api.milktea.repository.MeasureRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureService {
    @Autowired
    private MeasureRespository measureRespository;
    public Measure createMeasureService(String name) {
        Measure measure = new Measure();
        measure.setName(name);
        return measureRespository.save(measure);
    }

    public List<Measure> getAllMeasure() {
        return (List<Measure>) measureRespository.findAll();
    }

    public Measure updateMeasureService(String name,long id) {
        Measure measure = measureRespository.findById(id).get();
//        Measure measure = new Measure();
        measure.setName(name);
        return measureRespository.save(measure);
    }

    public Boolean deleteMeasureService(long id){
        try {
            measureRespository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public Measure findMeasureByID(long id) {
        return  measureRespository.findById(id).orElse(null);
    }

}
