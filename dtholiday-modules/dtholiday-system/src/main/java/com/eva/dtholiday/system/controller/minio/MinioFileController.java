package com.eva.dtholiday.system.controller.minio;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.system.service.minio.MinioService;

@RestController
@RequestMapping("/files")
public class MinioFileController {
    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public ResponseApi uploadFile(@RequestPart("file") MultipartFile file) {
        return minioService.uploadFile(file, file.getOriginalFilename());
    }

    @GetMapping("/download/{objectName}")
    public ResponseApi downloadFile(@PathVariable("objectName") String objectName, HttpServletResponse response) {
        return minioService.downloadFile(objectName, response);
    }
}
