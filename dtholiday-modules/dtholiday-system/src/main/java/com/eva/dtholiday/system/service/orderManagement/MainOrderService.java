package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderCancelReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderQueryListReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentReq;
import com.eva.dtholiday.commons.dao.resp.orderManagement.MainOrderQueryListResp;

public interface MainOrderService {
    ResponseApi addMainOrder(MainOrderReq req);

    ResponseApi<MainOrderQueryListResp> queryMainOrderList(MainOrderQueryListReq req);

    ResponseApi agentPay(PaymentReq req);

    ResponseApi cancelMainOrderByAgent(MainOrderCancelReq req);

    ResponseApi cancelMainOrderBySalesman(MainOrderCancelReq req);

    ResponseApi cancelMainOrderByFinancialMan(MainOrderCancelReq req);
}
