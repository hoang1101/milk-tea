package com.api.milktea.cloudinary;

import com.api.milktea.services.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class CloudinaryController {

    private final CloundinaryInterface cloundinaryInterface;

    @PostMapping("/upload")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("image")MultipartFile multipartFile) throws Exception{
        Map<String,String> imageURL = cloundinaryInterface.uploadFile(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(imageURL, "Tao thanh cong", "true")
        );
    }
}
