package com.eva.dtholiday.commons.dao.resp.fileinfo;

import lombok.Data;

@Data
public class FileInfoResp {
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * 文件名
     */
    private String fileName;

    /**
     *
     */
    private String fileType;
}
