package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@TableName("dt_island_quotation_relation")
@EqualsAndHashCode(callSuper = true)
public class IslandQuotationRelation extends Model<IslandQuotationRelation> {
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
     * 报价单
     */
    private int quotationIndexCode;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public static final String ISLAND_INDEX_CODE = "island_index_code";
    public static final String QUOTATION_INDEX_CODE = "quotation_index_code";
}
