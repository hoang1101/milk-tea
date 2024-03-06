package com.api.milktea.services;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AuthObject {

    private static AuthObject instance;

    private AuthObject() {
        // private constructor to prevent instantiation
    }

    public static synchronized AuthObject getInstance() {
        if (instance == null) {
            instance = new AuthObject();
        }
        return instance;
    }

    public ResponseEntity<Object> generateAuthenicationResponse(String message, HttpStatus status, Object responseObj, String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("token", token);

        return new ResponseEntity<>(map, status);
    }
}
