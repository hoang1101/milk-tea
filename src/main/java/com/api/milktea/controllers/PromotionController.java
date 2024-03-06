package com.api.milktea.controllers;

import com.api.milktea.models.Promotion;
import com.api.milktea.services.ProductService;
import com.api.milktea.services.PromotionService;
import com.api.milktea.services.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createPromotion(
            @RequestBody Map<?, ?> body
    ) {
        try {
            float staffIdFloat = ((Integer) body.get("percent")).floatValue();
            Float percent = Float.valueOf(staffIdFloat);

            Integer intValue = (Integer) body.get("staff_id");
            Long staff_id = Long.valueOf(intValue);

            Object date = body.get("start_day");

            String birthDateString = (String) date;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date start_day = dateFormat.parse(birthDateString);
            System.out.println(start_day);

            Object date_end = body.get("end_day");
            String birthDateString1 = (String) date;
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            Date end_day = dateFormat1.parse(birthDateString);
            System.out.println(end_day);

            ArrayList<Integer> data = (ArrayList<Integer>) body.get("data");

            for (Integer item : data) {
                Long longValue = Long.valueOf(item);
                promotionService.createPromotionService(longValue, staff_id, percent, start_day, end_day);
            }


            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(data, "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deletePromotion(
            @PathVariable long id
    ) {
        try {
            if (promotionService.deletePromotionService(id)) {

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(id, "Xóa thành công", "true")
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(id, "Không tồn tại sản phẩm", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-promotion")
    public ResponseEntity<ResponseObject> getAllPromotion(

    ) {
        try {
            List<Promotion> promotions = promotionService.getAllPromotionService();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(promotions, "Không tồn tại sản phẩm", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }



}

