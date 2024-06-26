package com.eva.dtholiday.commons.dao.req.orderManagement;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 21:50
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketOrderSalesmanReq {

    private Integer planeTicketOrderId;
    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 优惠金额
     */
    private Double discount;

    /**
     * 机票号
     */
    private String ticketNumber;

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
