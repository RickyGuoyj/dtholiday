package com.eva.dtholiday.commons.dao.req.orderManagement;

import lombok.Data;

@Data
public class MainOrderQueryListReq {
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页多少条
     */
    private int pageSize;

    private String orderCreator;

    private Integer mainOrderId;

    private String saleMan;
    private Integer islandOrderId;
    private Integer planeTicketOrderId;
    private Integer transitionHotelOrderId;
}
