package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
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
     * 副标题
     */
    private String subtitle;


    /**
     * 0-探索马代 1-岛屿文章（最新消息） 2-灯塔游记
     */
    private Integer type;

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
    private Integer islandIndexCode;

    /**
     * 岛屿管理名称
     */
    private String islandCnName;

    /**
     * 链接
     */
    private String links;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
