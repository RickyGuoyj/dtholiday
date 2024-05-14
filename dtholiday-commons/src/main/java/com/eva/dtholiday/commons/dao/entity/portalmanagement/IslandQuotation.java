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
@TableName("dt_island_quotation")
@EqualsAndHashCode(callSuper = true)
public class IslandQuotation extends Model<IslandQuotation> {
    private static final long serialVersionUID = 1L;

    /**
     * 报价唯一标识码（主键）
     */
    @TableId(value = "quotation_index_code", type = IdType.AUTO)
    private Integer quotationIndexCode;

    /**
     * 关联岛屿唯一标识码（外键，关联岛屿表的island_index_code）
     */
    private int islandIndexCode;

    /**
     * 报价单类型 0-图片 1-pdf
     */
    private int quotationType;

    /**
     * 报价单名称
     */
    private String quotationName;

    /**
     * 报价文件路径或相关文件信息(url)
     */
    private String quotationFile;

    /**
     * 创建时间戳，默认为当前时间
     */
    private Timestamp createTime;

    /**
     * 更新时间戳，默认为当前时间
     */
    private Timestamp updateTime;

    public static final String ISLAND_INDEX_CODE = "island_index_code";

    public static final String QUOTATION_NAME = "quotation_name";
}
