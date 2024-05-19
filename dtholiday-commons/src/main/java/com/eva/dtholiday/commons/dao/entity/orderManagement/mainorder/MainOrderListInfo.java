package com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder;

import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MainOrderListInfo {
    private Integer mainOrderId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String orderCreator;
    private String saleMan;
    private TotalPriceInfo totalPrice;
    private Integer islandOrderId;
    private Double islandTotalPrice;
    private Integer islandCurrencyType;
    private Integer planeTicketOrderId;
    private Double planeTicketTotalPrice;
    private Integer planeTicketCurrencyType;
    private Integer transitionHotelOrderId;
    private Double transitionHotelTotalPrice;
    private Integer transitionHotelCurrencyType;
    private Integer orderStatus;
    private Integer financialStatus;
    private Timestamp paymentTime;
}
