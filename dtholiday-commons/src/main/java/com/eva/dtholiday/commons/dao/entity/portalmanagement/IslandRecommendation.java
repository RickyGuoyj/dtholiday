package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fengsuohua
 */
@Data
@TableName("dt_island_recommendation")
@EqualsAndHashCode(callSuper = true)

public class IslandRecommendation extends Model<IslandRecommendation> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "recommendation_index_code", type = IdType.AUTO)
    private Integer recommendationIndexCode;

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
    private int islandIndexCode;

    /**
     * 推荐文件路径或相关文件信息
     */
    private String recommendationImage;

    /**
     * 创建时间戳，默认为当前时间
     */
    private Timestamp createTime;

    /**
     * 更新时间戳，默认为当前时间
     */
    private Timestamp updateTime;

    public static final String ISLAND_INDEX_CODE = "island_index_code";
}
