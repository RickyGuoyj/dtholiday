package com.eva.dtholiday.commons.dao.entity.systemmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_currency_management")
public class Currency extends Model<Currency> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "currency_index_code", type = IdType.AUTO)
    private Integer currencyIndexCode;
    private String currencyName;
    private String currencyCode;
    private String countryName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
