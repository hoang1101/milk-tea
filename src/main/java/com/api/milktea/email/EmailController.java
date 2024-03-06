package com.api.milktea.email;

import com.api.milktea.services.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")

public class EmailController {
    @Autowired
    private EmailService service;


    @PostMapping("/send-mail")
    public ResponseEntity<ResponseObject> sendEmail(@Valid @RequestBody Email email) {
        Map<String, Object> model = new HashMap<>();
//        model.put("name", "Nguyễn Minh Duy");
//        model.put("content", "<p>This is a <strong>complex</strong> email with HTML content and CSS styling.</p>");
        service.sendEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(email, "Tao thanh cong", "true")
        );
    }
}
