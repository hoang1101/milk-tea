package com.api.milktea.cloudinary;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface CloundinaryInterface {
    Map<String,String> uploadFile(MultipartFile multipartFile) throws IOException;

    Boolean  deleteFile(String public_id) throws IOException;


}
