package com.ra.demo3.service.updateFile;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class UpdateFileService {
    public String updateFile(MultipartFile multipartFile) {
        String uploadPath = "D:\\java_Md3\\demo3\\src\\main\\resources\\uploads";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return fileName;
    }
}