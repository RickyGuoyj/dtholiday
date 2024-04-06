package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class IslandTagReq {
    /**
     * 主键
     */
    private String tagIndexCode;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签图片
     */
    private String tagUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
