package com.eva.dtholiday.commons.dao.req.financialManagement;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/25 17:23
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PaymentCheckReq {

    private int paymentId;

    /**
     * 0-失败， 1-成功
     */
    private Integer checkStatus;

    /**
     * 审批意见
     */
    private String checkRemark;
}
