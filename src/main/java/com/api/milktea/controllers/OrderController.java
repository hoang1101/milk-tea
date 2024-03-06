package com.api.milktea.controllers;

import com.api.milktea.models.Order;
import com.api.milktea.models.User;
import com.api.milktea.services.OrderItemService;
import com.api.milktea.services.OrderService;
import com.api.milktea.services.ResponseObject;
import com.api.milktea.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createOrder (
            @RequestBody Map<?, ?> body
            )
    {
        try {
            String address = (String) body.get("address");
            Integer integer = (Integer) body.get("customerId");

            Order order = orderService.createOrder(Long.valueOf(integer),address);
            Long orderGet = order.getId();

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) body.get("data");
            for (Map<String, Object> item : dataList) {
                Integer integerValue = (Integer) item.get("productId");
                Long productId = integerValue.longValue();
                orderItemService.createOrderItem((Integer) item.get("price"), (Integer) item.get("quantity"),orderGet, productId);

            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("products", "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> staffAcceptOrder (
            @PathVariable long id,
            @RequestBody Map<String, Object> staff_id
    ) {

        try {
            Object staffIdObj = staff_id.get("staff_id");
            System.out.println(staffIdObj);
            long staffId = ((Integer) staffIdObj).longValue();
            Long staffIdLong = Long.valueOf(staffId);
            Order order = orderService.acceptOrderService(id, staffIdLong);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("order", "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


}
