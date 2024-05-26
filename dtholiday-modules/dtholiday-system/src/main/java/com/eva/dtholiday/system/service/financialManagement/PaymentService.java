package com.eva.dtholiday.system.service.financialManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentCheckReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentDetailReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentPageReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentReq;
import com.eva.dtholiday.commons.dao.resp.financialManagement.PaymentResp;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 15:13
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface PaymentService {
    ResponseApi queryPaymentList(PaymentPageReq req);

    ResponseApi updateFinancialStatusByFinancialMan(PaymentCheckReq req);

    PaymentResp queryPaymentDetail(PaymentDetailReq req);

    ResponseApi agentPay(PaymentReq req);
}
