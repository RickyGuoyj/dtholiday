package com.eva.dtholiday.commons.dao.req.orderManagement;

import lombok.Data;

@Data
public class IslandHotelOrderQueryListReq {
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页多少条
     */
    private int pageSize;

    private String customerName;

    private String islandCnName;

    private Integer orderStatus;
    private Integer financialStatus;

    private Integer islandHotelOrderId;

}
