package com.eva.dtholiday.system.service.minio.impl;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.system.service.minio.MinioService;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;

    @Override
    public ResponseApi uploadFile(MultipartFile file, String objectName) {
        try {
            // 检查文件是否已存在，如果存在则重命名
            String newName = resolveFileName(objectName);
            InputStream stream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(newName)
                .stream(stream, file.getSize(), -1).contentType(file.getContentType()).build());
            String filePath = endpoint + "/" + bucketName + "/" + newName;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath(filePath);
            fileInfo.setFileName(objectName);
            return ResponseApi.ok(fileInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }

    @Override
    public ResponseApi downloadFile(String objectName, HttpServletResponse response) {
        try {
            InputStream stream =
                minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            IOUtils.copy(stream, response.getOutputStream());
            response.flushBuffer();
            return ResponseApi.ok(1);
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file: " + e.getMessage());
        }
    }

    private String resolveFileName(String objectName) throws Exception {
        String extension = "";
        int lastDotIndex = objectName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            extension = objectName.substring(lastDotIndex);
            objectName = objectName.substring(0, lastDotIndex);
        }
        objectName += "-" + System.currentTimeMillis() + extension;
        return objectName;
    }
}
