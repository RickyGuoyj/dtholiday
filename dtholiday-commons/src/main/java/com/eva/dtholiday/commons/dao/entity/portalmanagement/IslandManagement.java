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
@EqualsAndHashCode(callSuper = true)
@TableName("dt_island")
public class IslandManagement extends Model<IslandManagement> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "island_index_code", type = IdType.AUTO)
    private Integer islandIndexCode;

    /**
     * 岛屿中文名
     */
    private String islandCnName;
    /**
     * 岛屿英文名
     */
    private String islandEnName;
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
     * 岛屿资料链接
     */
    private String islandFile;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public static final String ISLAND_INDEX_CODE = "island_index_code";
    public static final String ISLAND_CN_NAME = "island_cn_name";

    public static final String ISLAND_EN_NAME = "island_en_name";
}
