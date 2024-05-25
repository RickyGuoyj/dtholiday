package com.eva.dtholiday.system.service.easyexcel;

import org.springframework.web.multipart.MultipartFile;

import com.eva.dtholiday.commons.api.ResponseApi;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public interface EasyExcelStrategy {
    void exportExcel(HttpServletResponse response);

    ResponseApi importExcel(MultipartFile file);

    /**
     * 设置响应结果
     *
     * @param response 响应结果对象
     * @param rawFileName 文件名
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    public default void setExcelResponseProp(HttpServletResponse response, String rawFileName)
        throws UnsupportedEncodingException {
        // HttpServletResponse消息头参数设置
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String fileName = rawFileName + ".xlsx";
        fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    }

}
