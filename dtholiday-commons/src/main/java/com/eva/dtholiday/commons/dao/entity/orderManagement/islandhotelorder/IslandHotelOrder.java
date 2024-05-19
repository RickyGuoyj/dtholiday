package com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_order_island")
public class IslandHotelOrder extends Model<IslandHotelOrder> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "island_hotel_order_id", type = IdType.AUTO)
    private Integer islandHotelOrderId;
    private Integer orderType;
    private String customerName;
    private Integer nights;
    private Integer adultNum;
    private Integer childNum;
    private Integer firstChildAge;
    private Integer secondChildAge;
    private Integer islandIndexCode;
    private String islandCnName;
    private Integer trafficType;
    private Integer mealType;
    private String hotelRoomType;
    private String delayHotelRoomType;
    private Date effectiveDate;
    private Date expiryDate;
    private Double totalPrice;
    private Double discountPrice;
    private Integer currencyType;
    private Double costPrice;
    private Double discount;
    private String bookingCode;
    private Integer orderStatus;
    private Integer financialStatus;
    private String saleMan;
    private String financialMan;
    private String orderCreator;
    private String confirmInfo;
    private String remarks;
    private Timestamp createTime;
    private Timestamp updateTime;
}
