package com.eva.dtholiday.commons.dao.req.orderManagement;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 22:01
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelOrderSalesmanReq {

    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 优惠金额
     */
    private Double discount;

    /**
     * 酒店预定号
     */
    private String bookingCode;

    /**
     * 财务人员
     */
    private String financialMan;

    /**
     * 0-失败， 1-成功
     */
    private Integer checkStatus;

    /**
     * 审批意见
     */
    private String checkRemark;
}
