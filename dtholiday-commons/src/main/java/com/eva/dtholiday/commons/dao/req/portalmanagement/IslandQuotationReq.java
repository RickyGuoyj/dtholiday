package com.eva.dtholiday.commons.dao.req.portalmanagement;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class IslandQuotationReq {

    /**
     * 报价唯一标识码（主键）
     */
    private int quotationIndexCode;

    /**
     * 关联岛屿唯一标识码（外键，关联岛屿表的island_index_code）
     */
    private int islandIndexCode;

    /**
     * 报价单名称
     */
    private String quotationName;
    /**
     * 报价文件路径或相关文件信息
     */
    private String quotationFile;

    /**
     * 创建时间戳，默认为当前时间
     */
    private Timestamp createTime;

    /**
     * 更新时间戳，默认为当前时间
     */
    private Timestamp updateTime;

    private int page;
    private int pageSize;
}
