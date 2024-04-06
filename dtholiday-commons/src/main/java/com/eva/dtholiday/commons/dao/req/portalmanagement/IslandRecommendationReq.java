package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author fengsuohua
 */
@Data
public class IslandRecommendationReq {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 岛屿中文名
     */
    private String islandCnName;

    /**
     * 岛屿英文名
     */
    private String islandEnName;

    /**
     * 岛屿唯一标识码
     */
    private String islandIndexCode;

    /**
     * 推荐文件路径或相关文件信息
     */
    private String recommendationFile;

    /**
     * 创建时间戳，默认为当前时间
     */
    private Timestamp createTime;

    /**
     * 更新时间戳，默认为当前时间
     */
    private Timestamp updateTime;
}
