package com.api.milktea.services;

import com.api.milktea.models.Evaluate;
import com.api.milktea.repository.EvaluateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluateService {

    @Autowired
    private EvaluateRepository evaluateRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    public Evaluate createEvaluateService(
            String comment,
//            String image_url,
//            String public_id,
            float star,
            long orderitem_id,
            long product_id,
            long user_id
    ) {
        Evaluate evaluate = new Evaluate();
        evaluate.setComment(comment);
//        evaluate.setImageUrl(image_url);
//        evaluate.setPublicId(public_id);
        evaluate.setStar(star);
        evaluate.setDate(new Date());
        evaluate.setOrderItem(orderItemService.getOrderItemByIdService(orderitem_id).get());
        evaluate.setProduct(productService.getProductById(product_id).get());
        evaluate.setUser(userService.getUserById(user_id));
        return evaluateRepository.save(evaluate);
    }

    public Optional<Evaluate> getEvaluateById(long id) {
        return evaluateRepository.findById(id);
    }

    public Evaluate updateEvaluateService(
            long id,
            String comment,
//            String image_url,
//            String public_id,
            float star
    )
    {
        Evaluate evaluate = getEvaluateById(id).get();
        evaluate.setDate(new Date());
        evaluate.setStar(star);
        evaluate.setComment(comment);
        return evaluateRepository.save(evaluate);
    }

    public List<Evaluate> getEvaluateByIdProduct(long id) {
        return evaluateRepository.findByProductId(id);
    }
}
