package com.eva.dtholiday.commons.dao.entity.productManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/28 1:29
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_extra_child_expense")
public class ExtraChildExpense extends Model<ExtraChildExpense> {
    /**
     * 额外儿童费用ID
     */
    @TableId(value = "extra_child_expense_id", type = IdType.AUTO)
    private Integer extraChildExpenseId;

    /**
     * 开始年龄
     */
    private String startAge;

    /**
     * 结束年龄
     */
    private String endAge;

    /**
     * 费用价格
     */
    private double price;

    /**
     * 货币类型
     */
    private int currencyType;

    /**
     * 额外儿童延迟酒店价格
     */
    private double extraChildDelayHotelPrice;

    /**
     * 额外儿童延迟货币类型
     */
    private int extraChildDelayCurrencyType;

    /**
     * 额外儿童酒店价格
     */
    private double extraChildHotelPrice;

    /**
     * 额外儿童交通价格
     */
    private double extraChildTrafficPrice;

    /**
     * 额外儿童餐饮价格
     */
    private double extraChildMealPrice;

    /**
     * 额外儿童环保税
     */
    private double extraChildEnvTax;

    /**
     * 额外儿童收费
     */
    private double extraChildCharge;

    /**
     * 岛屿索引代码
     */
    private Integer islandIndexCode;

    /**
     * 岛屿中文名称
     */
    private String islandCnName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
