package com.api.milktea.services;

import com.api.milktea.models.Promotion;
import com.api.milktea.repository.ProductsRepository;
import com.api.milktea.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    public Promotion createPromotionService(
            long product_id,
            long staff_id,
            float percent,
            Date start_day,
            Date end_day
    ) {
        Promotion promotion = new Promotion();
        promotion.setStaff(userService.getUserById(staff_id));
        promotion.setProduct(productService.getProductById(product_id).get());
        promotion.setPrecent(percent);
        promotion.setStart_day(start_day);
        promotion.setEnd_day(end_day);
        return promotionRepository.save(promotion);
    }

    public Boolean deletePromotionService(long id){
        try {
            promotionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public List<Promotion> getAllPromotionService() {
        return (List<Promotion>) promotionRepository.findAll();
    }
}
