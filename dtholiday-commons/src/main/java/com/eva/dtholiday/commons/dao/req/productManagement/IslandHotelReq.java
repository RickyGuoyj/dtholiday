package com.eva.dtholiday.commons.dao.req.productManagement;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/29 0:53
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandHotelReq {

    @ExcelProperty(value = "编号")
    private Integer islandHotelId;

    /**
     * 岛屿编码
     */
    @ExcelProperty(value = "岛屿ID")
    private Integer islandIndexCode;

    /**
     * 岛屿中文名称
     */
    @ExcelProperty(value = "岛屿产品名称")
    private String islandCnName;

    /**
     * 价格类型：1-打包价，2-合同价
     */
    @ExcelProperty(value = "价格类型")
    private Integer priceType;

    /**
     * 上岛工具类型
     */
    @ExcelProperty(value = "交通工具类型")
    private Integer trafficType;

    /**
     * 餐型类型
     */
    @ExcelProperty(value = "餐型")
    private Integer mealType;

    /**
     * 房型描述
     */
    @ExcelProperty(value = "房型")
    private String hotelRoomType;

    /**
     * 是否含环境税：0-不含，1-含
     */
    @ExcelProperty(value = "是否含环境税")
    private Integer hasEnvironmentTax;

    /**
     * 生效日期
     */
    @ExcelProperty(value = "生效时间")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    /**
     * 失效日期
     */
    @ExcelProperty(value = "失效时间")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;

    /**
     * 总量
     */
    @ExcelProperty(value = "总产品数")
    private Integer totalNum;

    /**
     * 打包间夜数
     */
    @ExcelProperty(value = "打包间夜数")
    private Integer packageNights;

    /**
     * 币种类型
     */
    @ExcelProperty(value = "币种")
    private Integer currencyType;

    /**
     * 打包价格
     */
    @ExcelProperty(value = "打包价")
    private double packagePrice;

    /**
     * 往返交通费
     */
    @ExcelProperty(value = "往返交通价格")
    private double trafficPrice;

    /**
     * 餐饮价格
     */
    @ExcelProperty(value = "餐饮价格")
    private double mealPrice;

    /**
     * 环境税
     */
    @ExcelProperty(value = "环境税")
    private double environmentTax;

    /**
     * 附加费
     */
    @ExcelProperty(value = "附加税")
    private double extraExpense;

    /**
     * 额外成人酒店价格
     */
    @ExcelProperty(value = "额外成人酒店价格")
    private double extraAdultHotelPrice;

    /**
     * 额外成人交通价格
     */
    @ExcelProperty(value = "额外成人往返交通价格")
    private double extraAdultTrafficPrice;

    /**
     * 额外成人餐饮价格
     */
    @ExcelProperty(value = "额外成人餐饮价格")
    private double extraAdultMealPrice;

    /**
     * 额外成人环境税
     */
    @ExcelProperty(value = "额外成人环境税")
    private double extraAdultEnvironmentTax;

    /**
     * 额外成人打包价
     */
    @ExcelProperty(value = "额外成人打包价")
    private double extraAdultPackagePrice;

    /**
     * 额外成人币种
     */
    @ExcelProperty(value = "额外成人价格币种")
    private Integer extraAdultCurrencyType;

    /**
     * 本身的2人延住房型
     */
    @ExcelProperty(value = "延住房型")
    private String delayHotelRoomType;

    /**
     * 2人的延住1天的价格
     */
    @ExcelProperty(value = "延住价格")
    private double delayHotelRoomPrice;

    /**
     * 延住价格的币种
     */
    @ExcelProperty(value = "延住价格币种")
    private Integer delayCurrencyType;

    /**
     * 额外成人延住价格
     */
    @ExcelProperty(value = "额外成人延住价格")
    private double extraAdultDelayHotelRoomPrice;

    /**
     * 额外成人延住币种
     */
    @ExcelProperty(value = "额外成人延住价格币种")
    private Integer extraAdultDelayCurrencyType;

    /**
     * 特殊费用（如圣诞元旦）
     */
    @ExcelProperty(value = "特殊费用")
    private double specialPrice;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注")
    private String remarks;
}
