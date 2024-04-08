package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("dt_island_tag_relation")
@EqualsAndHashCode(callSuper = true)
public class IslandTagRelation extends Model<IslandTagRelation> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "relation_index_code", type = IdType.AUTO)
    private Integer relationIndexCode;

    /**
     * 岛屿管理主键
     */
    private int islandIndexCode;

    /**
     * 标签主键
     */
    private int tagIndexCode;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public static final String ISLAND_INDEX_CODE = "island_index_code";
    public static final String TAG_INDEX_CODE = "tag_index_code";
}
