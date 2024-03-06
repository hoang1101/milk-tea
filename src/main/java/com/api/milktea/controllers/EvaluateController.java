package com.api.milktea.controllers;

import com.api.milktea.cloudinary.CloundinaryInterface;
import com.api.milktea.models.Evaluate;
import com.api.milktea.services.EvaluateService;
import com.api.milktea.services.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/evaluate")
@RequiredArgsConstructor

public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;
    private final CloundinaryInterface cloundinaryInterface;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createEvaluate(
            @RequestBody Map<?, ?> body
//                @RequestParam(value = "image") MultipartFile multipartFile
    ) {
//        String public_id = "";
        try {
//            Map<String, String> imageURL = cloundinaryInterface.uploadFile(multipartFile);
//            public_id = imageURL.get("public_id");

            Integer orderitem_id = (Integer) body.get("orderitem_id");
            Integer product_id = (Integer) body.get("product_id");
            Integer user_id = (Integer) body.get("user_id");
            float staffIdFloat = ((Integer) body.get("star")).floatValue();
            Float star = Float.valueOf(staffIdFloat);
            Evaluate evaluate = evaluateService.createEvaluateService(
                    (String) body.get("comment"),
//                    public_id,
//                    imageURL.get("url"),
                    star,
                    Long.valueOf(orderitem_id),
                    Long.valueOf(product_id),
                    Long.valueOf(user_id)
            );

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(evaluate, "Update sản phẩm thành công", "true")
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateEvaluate(
            @RequestBody Map<?, ?> body,
            @PathVariable long id
    ) {
        try {
            float staffIdFloat = ((Integer) body.get("star")).floatValue();
            Float star = Float.valueOf(staffIdFloat);
            Evaluate evaluate = evaluateService.updateEvaluateService(
                    id,
                    (String) body.get("comment"),
//                    public_id,
//                    imageURL.get("url"),
                    star
            );
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(evaluate, "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @GetMapping("/get-evaluate/{id}")
    public ResponseEntity<ResponseObject> getAllEvaluateById(
            @PathVariable long id
    ) {
        try {
            List<Evaluate> evaluate = evaluateService.getEvaluateByIdProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(evaluate, "Update sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }
}
