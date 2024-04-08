package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class IslandTagReq {
    /**
     * 主键
     */
    private int tagIndexCode;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签图片
     */
    private String tagImage;

    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 当前页
     */
    private int page;
    /**
     * 每页条数
     */
    private int pageSize;
}
