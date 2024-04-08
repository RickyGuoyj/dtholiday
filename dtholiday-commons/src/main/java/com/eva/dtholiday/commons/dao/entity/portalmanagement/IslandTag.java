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
@TableName("dt_island_tag")
public class IslandTag extends Model<IslandTag> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "tag_index_code", type = IdType.AUTO)
    private Integer tagIndexCode;
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

    public static final String TAG_INDEX_CODE = "tag_index_code";
}
