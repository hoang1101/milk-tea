package com.api.milktea.controllers;

import com.api.milktea.services.MeasureService;
import com.api.milktea.services.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/measure")
public class MeasureController {
    @Autowired
    private MeasureService measureService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createMeasure(@RequestParam("name") String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(measureService.createMeasureService(name), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllMeasure() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(measureService.getAllMeasure(), "Update sản phẩm thành công", "true")
            );
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateMeasure(
            @PathVariable long id,
            @RequestParam("name") String name){
        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(measureService.updateMeasureService(name,id), "Update sản phẩm thành công", "true")
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteMeasure(@PathVariable long id) {
        try {
            if(measureService.deleteMeasureService(id)){

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(id,"Xóa thành công", "true")
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(id,"Không tồn tại sản phẩm", "true")
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }
}
