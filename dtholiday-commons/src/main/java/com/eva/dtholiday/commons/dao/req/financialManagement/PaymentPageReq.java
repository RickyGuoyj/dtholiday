package com.eva.dtholiday.commons.dao.req.financialManagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 15:09
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PaymentPageReq extends BasePage {

    private Integer financialStatus; // 0-待审核 1-销售审核通过 2-销售审核失败 3-财务审核通过 4-财务审核失败
    private Date startTime;
    private Date endTime;
    private String paymentType; // 支付方式
    private String saleMan; // 销售员

}
