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
@TableName("dt_island_article")
public class IslandArticle extends Model<IslandArticle> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "article_index_code", type = IdType.AUTO)
    private Integer articleIndexCode;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否显示
     */
    private String isDisplay;
    /**
     * 关联图片
     */
    private String articleImages;
   /**
     * 岛屿管理主键
     */
    private String islandIndexCode;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
