package com.api.milktea.controllers;

import com.api.milktea.cloudinary.CloundinaryInterface;
import com.api.milktea.models.Product;
import com.api.milktea.services.ProductService;
import com.api.milktea.services.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    private final CloundinaryInterface cloundinaryInterface;

    @PostMapping("/create-product")
    public ResponseEntity<ResponseObject> createProduct(
            @RequestParam("price") float price,
            @RequestParam("description") String description,
            @RequestParam("name") String productName,
            @RequestParam(value = "image") MultipartFile multipartFile
    ) {
        String public_id = "";
        try {
            Map<String, String> imageURL = cloundinaryInterface.uploadFile(multipartFile);
            public_id = imageURL.get("public_id");

//            System.out.println(price);
//            System.out.println(description);
//            System.out.println(productName);
//            System.out.println(public_id);
//            System.out.println(imageURL.get("url"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(productService.createProductService(price, description, productName, public_id, imageURL.get("url")), "Tạo sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @GetMapping("/get-all-products")
    public ResponseEntity<ResponseObject> getALlProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(productService.getAllProductServiceTrue(), "Lấy danh sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<ResponseObject> getALlProducts(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(productService.getProductById(id), "Lấy sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchProducts(@RequestParam("keyword") String keyword) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(productService.searchProductsService(keyword), "Lấy sản phẩm thành công", "true")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    //update-product
    @PutMapping("/update-product/{id}")
    public ResponseEntity<ResponseObject> updateProduct(
            @PathVariable long id,
            @RequestParam("price") float price,
            @RequestParam("description") String description,
            @RequestParam("name") String productName,
            @RequestParam("active") boolean active,
            @RequestParam(value = "image") MultipartFile multipartFile
    ) {
        try {
            Product product = productService.getProductById(id).get();
            product.setPrice((int) price);
            product.setDescription(description);
            product.setActive(active);
            product.setName(productName);
            if (multipartFile != null) {
                cloundinaryInterface.deleteFile("milktea" + "/" + product.getPublicId());
                Map<String, String> upload = cloundinaryInterface.uploadFile(multipartFile);
                product.setImageUrl(upload.get("url"));
                product.setPublicId(upload.get("public_id"));
            }
            product = productService.CreateProductServiceVer2(product);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(product, "Update sản phẩm thành công", "true")
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable long id) {
        try {
            if(productService.deleteProductService(id)){

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
