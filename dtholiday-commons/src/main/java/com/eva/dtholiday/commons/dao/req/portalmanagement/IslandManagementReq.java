package com.eva.dtholiday.commons.dao.req.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;

import lombok.Data;

@Data
public class IslandManagementReq {
    /**
     * 岛屿管理主键
     */
    private int islandIndexCode;
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
    private List<Integer> islandTagList;
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
    private String islandImage;
    /**
     * 岛屿文件
     */
    private String islandFile;

    /**
     * 岛屿pdf报价单
     */
    private List<IslandQuotation> islandQuotationPdfList;
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页多少条
     */
    private int pageSize;
}
