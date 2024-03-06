package com.api.milktea.services;

import com.api.milktea.models.Order;
import com.api.milktea.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public Order createOrder(long customerId, String address) {
        Order order = new Order();
        order.setAddress(address);
        order.setUser( userService.getUserById((customerId)));
        order.setDate(new Date());
        order.setStatus(null);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }


    public Order acceptOrderService(long id, long staff_id) {
        Order order = orderRepository.findById(id).get();
        order.setStaff( userService.getUserById((staff_id)));
        order.setStatus(true);

        return orderRepository.save(order);

    }
}
