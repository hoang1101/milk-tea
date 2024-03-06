package com.api.milktea.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloundinaryService implements CloundinaryInterface {

    @Autowired
    private final Cloudinary cloudinary;

    @Override
    public Map<String, String> uploadFile(MultipartFile multipartFile) throws IOException {
//        return cloudinary.uploader()
//                .upload(multipartFile.getBytes(),
//                        Map.of("public_id",UUID.randomUUID().toString()))
//                .get("url")
//                .toString();
        String public_id = UUID.randomUUID().toString();


        String url_image = cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        ObjectUtils.asMap(
                                "public_id", public_id,
                                "folder", "milktea", // Specify the folder name
                                "transformation", new Transformation().width(320).height(320))
                )
                .get("url")
                .toString();

        Map<String, String> tmp = new HashMap<>();
        tmp.put("public_id", public_id);
        tmp.put("url", url_image);

        return tmp;
    }

    @Override
    public Boolean deleteFile(String public_id) throws IOException {
        Map map = cloudinary.uploader()
                .destroy(public_id, Map.of("public_id", public_id));
//        System.out.println(map.get("result"));

        if (map.get("result").toString().equalsIgnoreCase("ok")) {
            return true;
        }
        return false;

    }
}
