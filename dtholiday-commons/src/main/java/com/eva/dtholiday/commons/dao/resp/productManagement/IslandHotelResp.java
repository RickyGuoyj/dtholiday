package com.eva.dtholiday.commons.dao.resp.productManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/29 0:50
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandHotelResp {


    private Integer islandHotelId;

    /**
     * 岛屿中文名称
     */
    private String islandHotelName;

    /**
     * 价格类型：1-打包价，2-合同价
     */
    private Integer priceType;

    /**
     * 上岛工具类型
     */
    private Integer trafficType;

    /**
     * 餐型类型
     */
    private Integer mealType;

    /**
     * 房型描述
     */
    private String hotelRoomType;

    /**
     * 是否含环境税：0-不含，1-含
     */
    private Integer hasEnvironmentTax;

    /**
     * 生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    /**
     * 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;

    /**
     * 总量
     */
    private Integer totalNum;

    /**
     * 余量
     */
    private Integer remainNum;

    /**
     * 打包间夜数
     */
    private Integer packageNights;

    /**
     * 币种类型
     */
    private Integer currencyType;

    /**
     * 打包价格
     */
    private double packagePrice;

    /**
     * 往返交通费
     */
    private double trafficPrice;

    /**
     * 餐饮价格
     */
    private double mealPrice;

    /**
     * 环境税
     */
    private double environmentTax;

    /**
     * 附加费
     */
    private double extraExpense;

    /**
     * 额外成人酒店价格
     */
    private double extraAdultHotelPrice;

    /**
     * 额外成人交通价格
     */
    private double extraAdultTrafficPrice;

    /**
     * 额外成人餐饮价格
     */
    private double extraAdultMealPrice;

    /**
     * 额外成人环境税
     */
    private double extraAdultEnvironmentTax;

    /**
     * 额外成人打包价
     */
    private double extraAdultPackagePrice;

    /**
     * 额外成人币种
     */
    private Integer extraAdultCurrencyType;

    /**
     * 本身的2人延住房型
     */
    private String delayHotelRoomType;

    /**
     * 2人的延住1天的价格
     */
    private double delayHotelRoomPrice;

    /**
     * 延住价格的币种
     */
    private Integer delayCurrencyType;

    /**
     * 额外成人延住价格
     */
    private double extraAdultDelayHotelRoomPrice;

    /**
     * 额外成人延住币种
     */
    private Integer extraAdultDelayCurrencyType;

    /**
     * 特殊费用（如圣诞元旦）
     */
    private double specialPrice;

    /**
     * 备注信息
     */
    private String remarks;
}
