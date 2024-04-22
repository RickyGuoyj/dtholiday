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
@TableName("dt_meal_management")
public class Meal extends Model<Meal> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "meal_index_code", type = IdType.AUTO)
    private Integer mealIndexCode;
    private String mealName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
