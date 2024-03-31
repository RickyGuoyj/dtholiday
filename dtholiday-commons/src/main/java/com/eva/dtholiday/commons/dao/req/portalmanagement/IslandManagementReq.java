package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class IslandManagementReq {
    /**
     * 岛屿中文名
     */
    private String islandCnName;
    /**
     * 岛屿英文名
     */
    private String islandEnName;
    /**
     * 岛屿标签
     */
    private String islandTags;
    /**
     * 岛屿描述
     */
    private String islandDesc;
    /**
     * 岛屿简介
     */
    private String islandIntro;
    /**
     * 岛屿图片
     */
    private String islandPic;
    /**
     * 岛屿文件
     */
    private String islandFile;
    /**
     * 岛屿关联的报价单pdf
     */
    private List<String> islandQuotationPdfIndexCodes;
    /**
     * 岛屿关联的报价单图片
     */
    private List<String> islandQuotationPicIndexCodes;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
