package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderQueryListReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.commons.dao.resp.orderManagement.MainOrderQueryListResp;

public interface MainOrderService {
    ResponseApi addMainOrder(MainOrderReq req);

    ResponseApi<MainOrderQueryListResp> queryMainOrderList(MainOrderQueryListReq req);
}
