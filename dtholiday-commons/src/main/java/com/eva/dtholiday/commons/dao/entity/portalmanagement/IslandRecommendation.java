package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * @author fengsuohua
 */
@Data
@TableName("dt_island_recommendation")
public class IslandRecommendation extends Model<IslandRecommendation> {
    /**
     * 主键，自增ID
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
