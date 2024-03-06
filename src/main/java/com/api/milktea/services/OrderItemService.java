package com.api.milktea.services;

import com.api.milktea.models.OrderItem;
import com.api.milktea.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    public OrderItem createOrderItem (int price,int quantity, long order_id,long product_id){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder( orderService.getOrderById(order_id).get());
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);
        orderItem.setProduct(productService.getProductById(product_id).get());
        orderItem.setCapital_price(productService.getProductById(product_id).get().getCapital_price());
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> getOrderItemByIdService(long id){
        return orderItemRepository.findById(id);
    }
}
