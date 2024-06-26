package com.eva.dtholiday.commons.dao.entity.productManagement;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@TableName("dt_island_hotel")
public class IslandHotel extends Model<IslandHotel> {

    @TableId(value = "island_hotel_id", type = IdType.AUTO)
    private Integer islandHotelId;

    /**
     * 岛屿编码
     */
    private Integer islandIndexCode;

    /**
     * 岛屿产品名称
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
    private Date effectiveDate;

    /**
     * 失效日期
     */
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

    public static final String ISLAND_HOTEL_NAME = "island_hotel_name";

    public static final String EFFECTIVE_DATE = "effective_date";

    public static final String EXPIRY_DATE = "expiry_date";

    public static final String HOTEL_ROOM_TYPE = "hotel_room_type";

    public static final String ISLAND_INDEX_CODE = "island_index_code";

    public static final String REMAIN_NUM = "remain_num";

    public static final String ISLAND_HOTEL_ID = "island_hotel_id";

    public static final String PRICE_TYPE = "price_type";
    public static final String TRAFFIC_TYPE = "traffic_type";
    public static final String MEAL_TYPE = "meal_type";
    public static final String DELAY_HOTEL_ROOM_TYPE = "delay_hotel_room_type";
}
