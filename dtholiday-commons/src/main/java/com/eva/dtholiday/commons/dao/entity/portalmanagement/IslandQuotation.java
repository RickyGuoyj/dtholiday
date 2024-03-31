package com.eva.dtholiday.commons.dao.entity.portalmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * @author fengsuohua
 */
@Data
@TableName("dt_island_quotation")
public class IslandQuotation extends Model<IslandQuotation> {
    /**
     * 报价唯一标识码（主键）
     */
    private String quotationIndexCode;

    /**
     * 关联岛屿唯一标识码（外键，关联岛屿表的island_index_code）
     */
    private String islandIndexCode;

    /**
     * 报价文件路径或相关文件信息
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
}
