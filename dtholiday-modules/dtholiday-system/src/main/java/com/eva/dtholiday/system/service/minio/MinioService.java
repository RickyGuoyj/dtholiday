package com.eva.dtholiday.system.service.minio;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.eva.dtholiday.commons.api.ResponseApi;

public interface MinioService {

    public ResponseApi uploadFile(MultipartFile file, String objectName);

    public ResponseApi downloadFile(String objectName, HttpServletResponse response);
}
