package com.eva.dtholiday.commons.dao.req.orderManagement;

import lombok.Data;

@Data
public class MainOrderCancelReq {
    /**
     * 主订单id
     */
    private int mainOrderId;

    /**
     * 操作：1 通过 2：不通过
     */
    private int operType;
}
