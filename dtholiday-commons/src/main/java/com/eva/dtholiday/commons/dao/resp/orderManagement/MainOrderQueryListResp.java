package com.eva.dtholiday.commons.dao.resp.orderManagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrderListInfo;

import lombok.Data;

@Data
public class MainOrderQueryListResp {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页大小
     */
    private int pageSize;

    private List<MainOrderListInfo> mainOrderListInfoList;

    /**
     * 总数
     */
    private int total;
}
