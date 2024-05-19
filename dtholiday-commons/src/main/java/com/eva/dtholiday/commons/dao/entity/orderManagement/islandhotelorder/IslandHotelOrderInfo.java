package com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class IslandHotelOrderInfo {
    private Integer islandHotelOrderId;
    private Integer orderType;
    private String customerName;
    private Integer adultNum;
    private Integer childNum;
    private Integer firstChildAge;
    private Integer secondChildAge;
    private Integer nights;
    private Integer islandIndexCode;
    private String islandCnName;
    private Integer hasEnvironmentTax;
    private Integer trafficType;
    private String trafficName;
    private Integer mealType;
    private String mealName;
    private String hotelRoomType;
    private String delayHotelRoomType;
    private Date effectiveDate;
    private Date expiryDate;
    private Integer currencyType;
    private Double totalPrice;
    private Double discountPrice;
    private String bookingCode;
    private Double costPrice;
    private Double discount;
    private Integer orderStatus;
    private Integer financialStatus;
    private String saleMan;
    private String financialMan;
    private Timestamp createTime;
    private Timestamp updateTime;
}
