package com.eva.dtholiday.commons.dao.resp.orderManagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderListInfo;

import lombok.Data;

@Data
public class IslandHotelOrderQueryListResp {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页大小
     */
    private int pageSize;

    private List<IslandHotelOrderListInfo> islandHotelOrderListInfoList;

    /**
     * 总数
     */
    private int total;
}
