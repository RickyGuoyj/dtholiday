package com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MainOrderListInfo {

    private Integer mainOrderId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String orderCreator;

    private String saleMan;

    private String totalPrice;

    private Integer islandHotelOrderId;

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

    private Integer mainOrderCancelStatus;
}
