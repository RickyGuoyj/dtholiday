package com.eva.dtholiday.commons.dao.req.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotel.IslandHotelInfo;

import lombok.Data;

@Data
public class IslandHotelOrderReq {
    private int orderType;
    private CustomerInfo customerInfo;
    private IslandHotelInfo hotelInfo;
    private Double totalPrice;
    private Integer currencyType;
    private int orderStatus;
    private int financialStatus;
    private String saleMan;
    private String financialMan;
    private String orderCreator;
}
