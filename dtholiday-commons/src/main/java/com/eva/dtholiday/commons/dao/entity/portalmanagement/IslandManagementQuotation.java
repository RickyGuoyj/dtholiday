package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class IslandManagementQuotation {
    private Integer quotationIndexCode;
    /**
     * 关联岛屿唯一标识码（外键，关联岛屿表的island_index_code）
     */
    private int islandIndexCode;

    /**
     * 岛屿名称
     */
    private String islandCnName;
    /**
     * 报价单名称
     */
    private String quotationName;
    /**
     * 报价文件路径或相关文件信息(url)
     */
    private String quotationFile;
    /**
     * 更新时间戳，默认为当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp updateTime;
}
